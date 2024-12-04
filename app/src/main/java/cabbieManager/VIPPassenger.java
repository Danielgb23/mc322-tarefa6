package cabbieManager;

import java.time.LocalDateTime;

import utils.PassengerInfoGenerator;

public class VIPPassenger extends Passenger implements BenefitsControl {
    private LocalDateTime vipExpiration;
    private String passengerId;
    private String email;
    private String name;
    private String phone;

    public VIPPassenger() {
    }

    public LocalDateTime getVipExpiration() {
        return vipExpiration;
    }

    /**
     * Registers a vip passenger by generating random information.
     * This method assigns a random email, name, phone number,vip expiration date,
     * and user ID to the passenger.
     * 
     */
    @Override
    public void register() {
        PassengerInfoGenerator pass = new PassengerInfoGenerator();
        this.email = pass.getEmail();
        this.name = pass.getName();
        this.phone = pass.getPhone();
        this.passengerId = pass.getPassengerId();

        // parte do metodo diferente
        this.vipExpiration = pass.getExpirationDate();
        System.out.println("Pessoa passageira VIP " + this.passengerId + " (" + this.name + ") criada com sucesso");

    }

    /**
     * Gets the special VIP discount
     * 
     * @return a float with the value of the discount between 0.0-1.0
     */
    @Override
    public Float getDiscount() {
        PassengerInfoGenerator pass = new PassengerInfoGenerator();
        return (float) (pass.getDiscount());
    }
}
