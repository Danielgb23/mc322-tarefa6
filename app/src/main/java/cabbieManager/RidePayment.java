package cabbieManager;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import utils.LocalDateTimeAdapter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import exceptions.*;

@XmlRootElement(name = "PaymentMethod")
public class RidePayment implements Payment {

    private Ride ride;
    private String paymentId;
    private String rideId;
    private LocalDateTime rideStartTime;
    private float rideDistance;
    private float amount;
    private PaymentOption paymentMethod;

    public RidePayment() {

    }

    /**
     * Constructs a new {@code RidePayment} instance.
     * <p>
     * This constructor initializes a new payment for a ride, generating a unique
     * payment ID and
     * calculating the amount based on the distance of the ride and the payment
     * method.
     * </p>
     * 
     * @param ride          The object for the ride this payment belongs to.
     * @param rideStartTime The start time of the ride. Must not be {@code null}.
     * @param rideDistance  The distance traveled during the ride, in kilometers.
     * @param paymentMethod The payment method selected by the user (e.g., "credit",
     *                      "cash").
     * 
     */
    public RidePayment(Ride ride, LocalDateTime rideStartTime, float rideDistance, String paymentMethod) throws InvalidFeeException{
        this.ride = ride;
        this.paymentId = UUID.randomUUID().toString();
        this.rideId = ride.getRideId();
        this.rideStartTime = rideStartTime;
        this.rideDistance = rideDistance;
        this.paymentMethod = this.selectPaymentMethod(paymentMethod);
        System.out.println("Forma de pagamento selecionada: " + paymentMethod);

        if (this.rideStartTime == null) {
            throw new NullPointerException("Start time of the ride to be paid cannot be null");
        }

        this.amount = this.calculateValue();

    }

    /**
     * Selects a PaymentOption from a given string.
     * 
     * @param paymentMethod the name of the payment method
     * @return the selected PaymentOption
     */
    private PaymentOption selectPaymentMethod(String paymentMethod) {
        return PaymentOption.valueOfName(paymentMethod);
    }

    /**
     * Calculates the total amount of the ride payment.
     * 
     * <p>
     * The amount is calculated based on the ride distance and the payment method
     * selected by the user.
     * The algorithm used is as follows:
     * <ol>
     * <li>Identify the distance range using the following intervals: [0, 5], [5,
     * 10], [10, 15], [15, 20], [20, 25].</li>
     * <li>Select the initial and per km price based on the identified distance
     * range and the time of day (day or night).</li>
     * <li>Calculate the total amount by adding the initial price and the price per
     * km multiplied by the ride distance.</li>
     * <li>Apply the payment method fee to the total amount.</li>
     * </ol>
     * 
     * @return the calculated amount
     */
    public float calculateValue()  throws InvalidFeeException{

        final float[] PRECO_INICIAL_DIURNO = { 5.00f, 4.00f, 3.50f, 3.00f, 2.50f };
        final float[] PRECO_POR_KM_DIURNO = { 2.00f, 2.50f, 3.00f, 4.00f, 3.50f };
        final float[] PRECO_INICIAL_NOTURNO = { 6.00f, 5.00f, 4.50f, 4.00f, 3.50f };
        final float[] PRECO_POR_KM_NOTURNO = { 2.50f, 3.00f, 3.50f, 4.50f, 4.0f };
        final float DISTANCIAS_LIMITE[] = { 5, 10, 15, 20, 25 };

        // identifies the distance range
        int faixa = -1;

        for (int i = 0; i < DISTANCIAS_LIMITE.length; i++) {
            if (this.rideDistance <= DISTANCIAS_LIMITE[i]) {
                faixa = i;
                break;
            }
        }

        // stabilish the initial and per km price
        float precoInicial = isHorarioNoturno() ? PRECO_INICIAL_NOTURNO[faixa] : PRECO_INICIAL_DIURNO[faixa];
        float precoPorKm = isHorarioNoturno() ? PRECO_POR_KM_NOTURNO[faixa] : PRECO_POR_KM_DIURNO[faixa];

        // calculates the total amount considering the payment method fee
        float _amount = this.paymentMethod.calculatePaymentFee(precoInicial + (this.rideDistance * precoPorKm));
        this.amount = Math.round(_amount * 100) / 100.0f;


        // Verifies if passenger is VIP or Business and applies discount
        Passenger passenger = this.ride.getPassenger();
        if (passenger instanceof BenefitsControl) {
            this.amount = this.amount * ((BenefitsControl) passenger).getDiscount();
        }

        return this.amount;

    }

    private boolean isHorarioNoturno() {
        return this.rideStartTime.toLocalTime().isBefore(LocalTime.of(6, 0))
                || this.rideStartTime.toLocalTime().isAfter(LocalTime.of(18, 0));
    }

    /**
     * Processa o pagamento da corrida.
     */
    public void processPayment() {
        System.out.println("Valor da corrida definido: " + this.amount);
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRideId() {
        return rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    public LocalDateTime getRideStartTime() {
        return rideStartTime;
    }

    public void setRideStartTime(LocalDateTime rideStartTime) {
        this.rideStartTime = rideStartTime;
    }

    public float getRideDistance() {
        return rideDistance;
    }

    public void setRideDistance(float rideDistance) {
        this.rideDistance = rideDistance;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public PaymentOption getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentOption paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
