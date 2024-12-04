package cabbieManager;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.checkerframework.common.reflection.qual.ClassValBottom;

import com.google.common.base.Objects;

import utils.LocalDateTimeAdapter;

@XmlRootElement(name = "ride")
public class Ride {

    private String rideId;
    private Cabbie cabbie;
    private Vehicle vehicle;
    private String status;
    private Passenger passenger;

    // Adcionar campos do Trabalho3
    private Location pickupLocation;
    private Location dropLocation;
    private LocalDateTime startTime;
    private float distance;

    // Adicionar os métodos da classe Ride
    public Ride() {

    }

    public Ride(Passenger passenger) {
        this.passenger = passenger;
    }

    /**
     * Requests a ride by a passenger.
     * 
     * @param pickupLocation the location where the passenger wants to be picked up
     * @param dropLocation   the location where the passenger wants to be dropped
     *                       off
     * 
     *                       The ride status is set to "REQUESTED".
     *                       The startTime is set to the current time.
     * 
     *                       A message is printed to the console with the
     *                       information of the ride.
     */

    public void requestRide(String pickupLocation, String dropLocation) {

        this.rideId = UUID.randomUUID().toString();
        this.pickupLocation = this.returnLocation(pickupLocation);
        this.dropLocation = this.returnLocation(dropLocation);
        this.startTime = LocalDateTime.now();

        System.out.println("Corrida chamada por pessoa passageira " + this.passenger.getPassengerId() + " de "
                + pickupLocation + " para " + dropLocation);
        this.updateRideStatus("CHAMADA", null, null);

        this.distance = this.calculateDistance();

    }

    /**
     * Returns a Location given a location name.
     * 
     * @param locationName the name of the location
     * 
     *                     If the location is not found, a default value of
     *                     AEROPORTO is returned.
     * 
     * @return a Location object
     */
    private Location returnLocation(String locationName) {
        return Location.valueOfName(locationName);

    }

    /**
     * Calculates the distance between the pickup and drop locations.
     * 
     * The distance is calculated as the Euclidean distance between the two points.
     * 
     * @return the calculated distance.
     */
    public float calculateDistance() {

        int x_pickup = pickupLocation.getX();
        int y_pickup = pickupLocation.getY();

        int x_drop = dropLocation.getX();
        int y_drop = dropLocation.getY();

        float distance = (float) Math.sqrt(Math.pow(x_drop - x_pickup, 2) + Math.pow(y_drop - y_pickup, 2));
        distance = Math.round(distance * 100) / 100.0f;
        System.out.println(("Distância calculada: " + distance));
        return distance;
    }

    /**
     * Atualiza o status da corrida.
     * 
     * Se o status for "ACEPTED", armazena o ID do motorista e do veiculo que
     * aceitou a corrida.
     * 
     * @param status  o novo status da corrida
     * @param cabbie  Objeto do taxista se a ride tiver status "ACCEPTED"
     * @param vehicle Objeto do veiculo que aceitou a corrida, se status for
     *                "ACCEPTED"
     */
    public void updateRideStatus(String status, Cabbie cabbie, Vehicle vehicle) {
        this.status = status;

        if (status.equals("ACEITA")) {
            this.cabbie = cabbie;
            this.vehicle = vehicle;
            System.out.println(("Corrida aceita por pessoa motorista " + this.cabbie.getCabbieId()));
        } else {
            System.out.println("Status da corrida: " + this.status);
        }

    }

    public void completeRide() {
        System.out.println("Corrida finalizada");

    }

    @XmlElement(name = "pickupLocation")
    public Location getPickLocation() {
        return this.pickupLocation;
    }

    /**
     * Sets the pickup location of this ride.
     * 
     * @param pickupLocation The new pickup location.
     */
    public void setPickupLocation(Location pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    @XmlElement(name = "dropLocation")
    public Location getDropLocation() {
        return this.dropLocation;
    }

    /**
     * Sets the drop location of this ride.
     * 
     * @param dropLocation The new drop location.
     */
    public void setDropLocation(Location dropLocation) {
        this.dropLocation = dropLocation;
    }

    /**
     * Gets the ID of this ride.
     * 
     * @return the ID of this ride (a UUID)
     */
    @XmlElement(name = "rideId")
    public String getRideId() {
        return this.rideId;
    }

    public void setRideId(String rideId) {
        this.rideId = rideId;
    }

    /**
     * Gets the start time of this ride.
     * 
     * @return the start time of this ride (a LocalDateTime)
     */
    @XmlJavaTypeAdapter(value = LocalDateTimeAdapter.class)
    @XmlElement(name = "startTime")
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Gets the distance of this ride.
     * 
     * @return the distance of this ride (a float)
     */
    @XmlElement(name = "rideDistance")
    public float getRideDistance() {
        return this.distance;
    }

    @XmlElement(name = "passenger")
    public Passenger getPassenger() {
        return this.passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @XmlElement(name = "cabbie")
    public Cabbie getCabbie() {
        return cabbie;
    }

    public void setCabbie(Cabbie cabbie) {
        this.cabbie = cabbie;
    }

    @XmlElement(name = "vehicle")
    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @XmlElement(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement(name = "distance")
    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        Ride pas = (Ride) o;
        return Objects.equal(this.rideId, pas.getRideId());
    }

    @Override
    public String toString() {
        return "Ride: " + this.rideId;
    }
}
