
public class BankAccount {
    private String accountNumber;
    double balance;
    private final TransactionHistory transactionHistory;

    public BankAccount(String accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.transactionHistory = new TransactionHistory();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.addTransaction(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.addTransaction(new Transaction("Withdrawal", -amount));
            return true;
        }
        return false;
    }
    /*
    public boolean transfer(BankAccount destinationAccount, double amount) {
        if (balance >= amount) {
            balance -= amount;
            destinationAccount.deposit(amount);
            transactionHistory.addTransaction(new Transaction("Transfer to " + getAccountNumber(), -amount));
            destinationAccount.getTransactionHistory().addTransaction(new Transaction("Transfer from " + getAccountNumber(), amount));
            return true;
        }
        return false;
    }

     */


    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }
    /*
    public static BankAccount findRecipientAccount(String recipientAccountNumber, List<BankAccount> accountList) {
        for (BankAccount account : accountList) {
            if (account.getAccountNumber().equals(recipientAccountNumber)) {
                return account;
            }
        }
        return null;
    }

     */

}

