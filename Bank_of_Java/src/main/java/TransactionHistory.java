import java.sql.*;
public class TransactionHistory {
    public static void transactionHistory() {
        Statement stmt;

        try {

            stmt = AccountOwner.connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS transaction_history (transaction_id INT AUTO_INCREMENT, "
                    + "transaction_details VARCHAR(255), "
                    + "PRIMARY KEY (transaction_id))");

            stmt.executeUpdate("INSERT INTO transaction_history (transaction_details) VALUES ('" + BankAccount.transaction + "')");

            ResultSet rs = stmt.executeQuery("SELECT * FROM transaction_history");
            while (rs.next()) {
                String details = rs.getString("transaction_details");

                System.out.println(details);
                System.out.println("-----------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
