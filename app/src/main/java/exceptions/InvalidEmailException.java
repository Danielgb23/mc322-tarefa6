package exceptions;

//Implement Exception
public class InvalidEmailException extends Exception {
    public InvalidEmailException() {
        super("Invalid email inserted");
    }

    public InvalidEmailException(String msg) {
        super(msg);
    }
}