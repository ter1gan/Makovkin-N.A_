import java.sql.*;
import java.util.Date;
import java.util.*;


public class Bank{
    private Connection connection;
    private Map<String, Customer> customerMap;

    public Bank() {
        customerMap = new HashMap<String, Customer>();
        try {
            // Установите подключение к базе данных
            connection = DriverManager.getConnection("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:init.sql'", "username", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registerCustomer(Customer customer) {
        try {
            // Вставьте данные клиента в таблицу customers
            String query = "INSERT INTO customers (first_name, last_name, address, phone, username, password, registration_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
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

    public Customer login(String username, String password) {
        try {
            // Выберите данные клиента из таблицы customers по заданному username и password
            String query = "SELECT * FROM customers WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                Date registrationDate = resultSet.getDate("registration_date");
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


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Date date = new Date();

        Bank bank = new Bank();
        int choice;

        while (true) {
            System.out.println("\n-------------------");
            System.out.println("BANK    OF     JAVA");
            System.out.println("-------------------\n");
            System.out.println("1. Registrar cont.");
            System.out.println("2. Login.");
            System.out.println("3. Exit.");
            System.out.print("\nEnter your choice : ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter First Name : ");
                    String firstName = sc.nextLine();
                    System.out.print("Enter Last Name : ");
                    String lastName = sc.nextLine();
                    System.out.print("Enter Address : ");
                    String address = sc.nextLine();
                    System.out.print("Enter contact number : ");
                    String phone = sc.nextLine();
                    System.out.println("Set Username : ");
                    String username = sc.next();
                    while (bank.customerMap.containsKey(username)) {
                        System.out.println("Username already exists. Set again : ");
                        username = sc.next();
                    }
                    System.out.println("Set a password:");
                    String password = sc.next();
                    sc.nextLine();
                    Customer customer = new Customer(firstName, lastName, address, phone, username, password, date);
                    bank.registerCustomer(customer);
                    break;
                case 2:
                    System.out.print("Enter your username: ");
                    String loginUsername = sc.next();
                    System.out.print("Enter your password: ");
                    String loginPassword = sc.next();
                    sc.nextLine();


                    Customer loggedInCustomer = bank.login(loginUsername, loginPassword);
                    BankAccount selectedAccount = new BankAccount(loginUsername);
                    AccountOwner accountOwner = new AccountOwner(loginUsername);
                    accountOwner.addBankAccount(selectedAccount);
                    if (loggedInCustomer != null) {
                        System.out.println("Welcome, " + loggedInCustomer.getFullName() + "!");
                        int accountChoice;
                        // Добавьте код для работы с банковскими счетами здесь

                        do {
                            System.out.println("\n-------------------");
                            System.out.println("BANK ACCOUNT MENU");
                            System.out.println("-------------------\n");
                            System.out.println("1. Deposit funds.");
                            System.out.println("2. Withdraw funds.");
                            System.out.println("3. Transfer funds.");
                            System.out.println("4. Check balance.");
                            System.out.println("5. Show transaction history.");
                            System.out.println("6. Logout.");
                            System.out.print("\nEnter your choice : ");
                            accountChoice = sc.nextInt();

                            switch (accountChoice) {

                                case 1:
                                    if (selectedAccount != null) {
                                        System.out.print("Enter amount to deposit: ");
                                        double depositAmount = sc.nextDouble();
                                        selectedAccount.deposit(depositAmount);
                                        System.out.println("Funds deposited successfully!");
                                    } else {
                                        System.out.println("No account selected.");
                                    }
                                    break;

                                case 2:
                                    if (selectedAccount != null) {
                                        System.out.print("Enter amount to withdraw: ");
                                        double withdrawAmount = sc.nextDouble();
                                        selectedAccount.withdraw(withdrawAmount);
                                        System.out.println("Funds withdrawn successfully!");
                                    } else {
                                        System.out.println("No account selected.");
                                    }
                                    break;

                                case 3:
                                    /*
                                    if (selectedAccount != null) {
                                        System.out.print("Enter recipient's account number: ");
                                        String recipientAccountNumber = sc.next();
                                        System.out.print("Enter amount to transfer: ");
                                        double transferAmount = sc.nextDouble();
                                        AccountOwner accountOwner1 = new AccountOwner(recipientAccountNumber);
                                        BankAccount recipientAccount = BankAccount.findRecipientAccount(recipientAccountNumber, accountOwner1.getBankAccounts());  // Вызов метода transfer у экземпляра класса Customer
                                        if (recipientAccount != null) {
                                            selectedAccount.transfer(recipientAccount, transferAmount);
                                            System.out.println("Funds transferred successfully!");
                                        } else {
                                            System.out.println("Recipient's account not found.");
                                        }
                                    } else {
                                        System.out.println("No account selected.");
                                    }
                                    break;

                                     */

                                case 4:
                                    if (selectedAccount != null) {
                                        double balance = selectedAccount.getBalance();
                                        System.out.println("Account balance: " + balance);
                                    } else {
                                        System.out.println("No account selected.");
                                    }
                                    break;

                                case 5:
                                    if (selectedAccount != null) {
                                        List<Transaction> transactionHistory = selectedAccount.getTransactionHistory().getTransactions();
                                        if (transactionHistory.isEmpty()) {
                                            System.out.println("No transaction history found.");
                                        } else {
                                            System.out.println("Transaction history:");
                                            for (Transaction transaction : transactionHistory) {
                                                System.out.println("- Amount: " + transaction.getAmount() + ", Timestamp: " + transaction.getTimestamp());
                                            }
                                        }
                                    } else {
                                        System.out.println("No account selected.");
                                    }
                                    break;

                                case 6:
                                    selectedAccount = null;
                                    break;

                                default:
                                    System.out.println("Invalid choice. Please try again.");
                                    break;
                            }
                        } while (accountChoice != 3);

                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                    break;
            }
        }

    }
}



