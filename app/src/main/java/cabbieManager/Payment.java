package cabbieManager;
import exceptions.*;
public interface Payment {
    
    public float calculateValue() throws InvalidFeeException;
    public void processPayment();
    
}
