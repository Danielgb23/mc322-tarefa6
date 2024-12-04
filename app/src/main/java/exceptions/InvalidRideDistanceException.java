package exceptions;

//Implement Exception
public class InvalidRideDistanceException extends Exception {
    public InvalidRideDistanceException() {
        super("Invalid distance for the ride");
    }

    public InvalidRideDistanceException(String msg) {
        super(msg);
    }
}