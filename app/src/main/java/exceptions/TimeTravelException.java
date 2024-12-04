package exceptions;

//Implement Exception
public class TimeTravelException extends Exception {
    public TimeTravelException() {
        super("Invalid car model year inserted");
    }

    public TimeTravelException(String msg) {
        super(msg);
    }
}