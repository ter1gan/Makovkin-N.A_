import java.util.Date;

public class Transaction {
    private final String description;
    private final double amount;
    private final Date timestamp;

    public Transaction(String description, double amount) {
        this.description = description;
        this.amount = amount;
        this.timestamp = new Date();
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
    public Date getTimestamp() {
        return timestamp;
    }
}


