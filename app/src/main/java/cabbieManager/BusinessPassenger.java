package cabbieManager;

import utils.PassengerInfoGenerator;

public class BusinessPassenger extends Passenger implements BenefitsControl {
    private String passengerId;
    private String email;
    private String businessEmail;
    private String name;
    private String phone;

    public BusinessPassenger() {
    }

    /**
     * Registers a business passenger by generating random information.
     * This method assigns a random email, name, phone number,business email date,
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
        this.businessEmail = pass.getBusinessEmail();
        System.out
                .println("Pessoa passageira Business " + this.passengerId + " (" + this.name + ") criada com sucesso");

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
