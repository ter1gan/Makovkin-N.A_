import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phone;
    private final String username;
    private final String password;
    private final Date registrationDate;
    public final List<BankAccount> bankAccounts;


    public Customer(String firstName, String lastName, String address, String phone, String username, String password, Date registrationDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.registrationDate = registrationDate;
        this.bankAccounts = new ArrayList<>();

    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

}
