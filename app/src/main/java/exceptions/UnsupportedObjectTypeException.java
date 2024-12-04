package exceptions;

//Implement Exception

public class UnsupportedObjectTypeException extends Exception {
    public UnsupportedObjectTypeException() {
        super("Invalid object inserted");
    }

    public UnsupportedObjectTypeException(String msg) {
        super(msg);
    }
}