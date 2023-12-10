import java.sql.*;
import java.util.Date;

public class BankAccount {
    private final String accountNumber;
    static StringBuilder transaction;
    static Date date;
    double balance;
    public BankAccount() {
        transaction = new StringBuilder();
        this.accountNumber = getAccountNumber();
        date = new Date();
        this.balance = 0;
    }
    public static Statement statement(){
        Connection connection;
        Statement statement ;
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test;INIT=RUNSCRIPT FROM 'classpath:init.sql'", "username", "password");
            statement = connection.createStatement();

            String createTableQuery = "CREATE TABLE IF NOT EXISTS accounts ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "accountNumber VARCHAR(255) NOT NULL,"
                    + "balance DOUBLE DEFAULT 0.0"
                    + ")";
            statement.executeUpdate(createTableQuery);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }
    public static void openAccount(String accountNumber) throws SQLException {
        String checkAccountQuery = "SELECT COUNT(*) FROM accounts WHERE accountNumber = '" + accountNumber + "'";
        ResultSet resultSet = statement().executeQuery(checkAccountQuery);
        resultSet.next();
        int count = resultSet.getInt(1);
        if (count == 0) {
            String openAccountQuery = "INSERT INTO accounts (accountNumber) VALUES ('" + accountNumber + "')";
            statement().executeUpdate(openAccountQuery);
            System.out.println("Счет открыт: " + accountNumber);
        } else {
            System.out.println("Счет уже существует");
        }

        resultSet.close();
    }
    public static void closeAccount(String accountNumber) throws SQLException {
        String checkAccountQuery = "SELECT COUNT(*) FROM accounts WHERE accountNumber = '" + accountNumber + "'";
        ResultSet resultSet = statement().executeQuery(checkAccountQuery);
        resultSet.next();
        int count = resultSet.getInt(1);

        if (count > 0) {
            String closeAccountQuery = "DELETE FROM accounts WHERE accountNumber = '" + accountNumber + "'";
            statement().executeUpdate(closeAccountQuery);
            System.out.println("Счет закрыт: " + accountNumber);
        } else {
            System.out.println("Счет не существует: " + accountNumber);
        }

        resultSet.close();
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public static double getBalance(String accountNumber) throws SQLException {
        String query = "SELECT balance FROM accounts WHERE accountNumber = ?";
        double balance = 0.0;
        try (PreparedStatement statement = AccountOwner.connection.prepareStatement(query)) {
            statement.setString(1, accountNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    balance = resultSet.getDouble("balance");
                }
            }
        }
        return balance;
    }


    public void deposit(String accountNumber, double amount) throws SQLException {
        String query = "UPDATE accounts SET balance = balance + ? WHERE accountNumber = ?";

        try (PreparedStatement statement = AccountOwner.connection.prepareStatement(query)) {
            statement.setDouble(1, amount);
            statement.setString(2, accountNumber);
            statement.executeUpdate();
        }
        transaction.append(date).append("\n").append("Счет ").append(accountNumber)
                .append(" пополнен на сумму ").append(amount).append("\n");
        System.out.println("Счет " + accountNumber + " пополнен на сумму " + amount);
    }



     public void withdraw(String accountNumber, double amount) throws SQLException {
         String query = "UPDATE accounts SET balance = balance - ? WHERE accountNumber = ? AND balance >= ?";


         try (PreparedStatement statement = AccountOwner.connection.prepareStatement(query)) {
             statement.setDouble(1, amount);
             statement.setString(2, accountNumber);
             statement.setDouble(3, amount);
             int rowsAffected = statement.executeUpdate();

             if (rowsAffected > 0) {
                 transaction.append(date).append("\n").append("Сумма ").append(amount)
                         .append(" выведена со счета ").append(accountNumber).append("\n");
                 System.out.println("Сумма " + amount + " выведена со счета " + accountNumber);
             } else {
                 System.out.println("На счете " + accountNumber + " недостаточно средств.");
             }
         }
     }

    public static void transfer(String fromAccount, String toAccount, double amount) throws SQLException {
        String checkFromAccountQuery = "SELECT COUNT(*) FROM accounts WHERE accountNumber = '" + fromAccount + "'";
        ResultSet resultSetFromAccount = statement().executeQuery(checkFromAccountQuery);
        resultSetFromAccount.next();
        int fromAccountCount = resultSetFromAccount.getInt(1);

        String checkToAccountQuery = "SELECT COUNT(*) FROM accounts WHERE accountNumber = '" + toAccount + "'";
        ResultSet resultSetToAccount = statement().executeQuery(checkToAccountQuery);
        resultSetToAccount.next();
        int toAccountCount = resultSetToAccount.getInt(1);

        if (fromAccountCount > 0 && toAccountCount > 0) {
            String transferQuery = "UPDATE accounts SET balance = balance - " + amount + " WHERE accountNumber = '" + fromAccount + "'";
            statement().executeUpdate(transferQuery);

            transferQuery = "UPDATE accounts SET balance = balance + " + amount + " WHERE accountNumber = '" + toAccount + "'";
            statement().executeUpdate(transferQuery);
            transaction.append(date).append("\n").append("Перевод: ").
                    append(amount).append(" со счета ").
                    append(fromAccount).append(" к счету ").append(toAccount).append("\n");

            System.out.println("Перевод: " + amount + " со счета " + fromAccount + " к счету " + toAccount);
        } else {
            System.out.println("Один или оба счета не существуют");
        }
    }

    public static void generateSummary() throws SQLException {
        String selectAccountsQuery = "SELECT * FROM accounts";
        ResultSet resultSet = statement().executeQuery(selectAccountsQuery);

        while (resultSet.next()) {
            String resultAsString = resultSet.getString("accountNumber");
            double balance = resultSet.getDouble("balance");

            System.out.println("Счет: " + resultAsString);
            System.out.println("Баланс: " + balance);
            System.out.println("------------------------");
        }
        resultSet.close();
    }

}

