import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountOwner {

    static final Connection connection;
    static String query = "INSERT INTO customers (first_name, last_name, address, phone, username, password, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
    static {
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:init.sql'", "username", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registerCustomer(Customer customer) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getUsername());
            statement.setString(6, customer.getPassword());
            statement.setDate(7, new java.sql.Date(customer.getRegistrationDate().getTime()));
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Customer login(String username, String password) {
        try {
            String query = "SELECT * FROM customers WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                java.util.Date registrationDate = resultSet.getDate("registration_date");
                Customer customer = new Customer(firstName, lastName, address, phone, username, password, registrationDate);
                resultSet.close();
                statement.close();
                return customer;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}

