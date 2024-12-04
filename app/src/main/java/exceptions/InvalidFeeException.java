package exceptions;

//Implement Exception
public class InvalidFeeException extends Exception {
    public InvalidFeeException() {
        super("Invalid fee inserted");
    }

    public InvalidFeeException(String msg) {
        super(msg);
    }
}