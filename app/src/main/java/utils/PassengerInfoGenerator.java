package utils;
import java.time.LocalDateTime;
import java.util.*;


public class PassengerInfoGenerator implements InfoGenerator{

    private String[] names = {"Tiago", "Sofia", "Felipe", "Isabela", "Rodrigo", "Carolina", "Henrique", "Fernanda", "Mateus", "Gabriela"};
    private String[] emails = {"tiago.silva@email.com", "sofia.almeida@email.com", "felipe.oliveira@email.com", "isabela.ferreira@email.com", "rodrigo.santos@email.com", "carolina.martins@email.com", "henrique.costa@email.com", "fernanda.lima@email.com", "mateus.pereira@email.com", "gabriela.souza@email.com"};
    private String[] businessEmails = {"john.doe@techcorp.com","jane.smith@innovatelabs.org","michael.jones@nextgeninc.net","sarah.adams@enterprisesolutions.co","robert.brown@marketlead.biz","emily.taylor@businessworld.io","david.anderson@logisticspro.org","lisa.white@globalventures.com","steven.harris@fintechsolutions.co","laura.clark@digitalstrategy.biz"};
    private String[] phones = {"+55 13 91234-5678", "+55 27 92345-6789", "+55 85 93456-7890", "+55 47 94567-8901", "+55 62 95678-9012", "+55 98 96789-0123", "+55 95 97890-1234", "+55 67 98901-2345", "+55 84 99012-3456", "+55 12 90123-4567"};
    private LocalDateTime[] expirationDates = { LocalDateTime.of(2023, 10, 12, 10, 30),LocalDateTime.of(2023, 11, 5, 14, 45),LocalDateTime.of(2024, 1, 1, 0, 0),LocalDateTime.of(2024, 2, 14, 18, 0),LocalDateTime.of(2024, 3, 25, 9, 30),LocalDateTime.of(2024, 4, 8, 13, 45),LocalDateTime.of(2024, 5, 20, 16, 15),LocalDateTime.of(2024, 6, 30, 20, 0),LocalDateTime.of(2024, 7, 4, 7, 45),LocalDateTime.of(2024, 7, 4, 7, 45)};
    private double[] discounts = {0.01,0.05,0.1,0.15,0.2,0.25,0.3,0.35,0.4,0.45};

    private String name;
    private String email;
    private String businessEmail;
    private String phone;
    private String passengerId;
    private LocalDateTime expirationDate;
    private double discount;

    public PassengerInfoGenerator() {
        this.generateRandomInfo();
    }

    /**
     * Generates random information for a passenger.
     */
    public void generateRandomInfo() {

        Random ran = new Random();
        int idx = ran.nextInt(10);
        String passengerId = UUID.randomUUID().toString();

        this.name = names[idx];
        this.email = emails[idx];
        this.businessEmail = businessEmails[idx];
        this.phone = phones[idx];
        this.passengerId = passengerId;
        this.expirationDate = expirationDates[idx];
        this.discount = discounts[idx];

        return;
    }

    /**
     * Returns the name of the generated passenger.
     * @return The name of the passenger.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the email of the generated passenger.
     * @return The email of the passenger.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the businessEmail of the generated passenger.
     * @return The businessEmail of the passenger.
     */
    public String getBusinessEmail() {
        return this.businessEmail;
    }

    public LocalDateTime getExpirationDate(){
        return this.expirationDate;
    }

    public double getDiscount(){
        return this.discount;
    }

    /**
     * Returns the phone of the generated passenger.
     * @return The phone of the passenger.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Returns the passengerId of the generated passenger.
     * @return The passengerId of the passenger.
     */

    public String getPassengerId() {
        return this.passengerId;
    }
}

