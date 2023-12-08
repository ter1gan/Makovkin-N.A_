import java.util.ArrayList;
import java.util.List;

public class AccountOwner {
    private final String name;
    private final List<BankAccount> bankAccounts;

    public AccountOwner(String name) {
        this.name = name;
        this.bankAccounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void addBankAccount(BankAccount bankAccount) {
        bankAccounts.add(bankAccount);
    }

    public void removeBankAccount(BankAccount bankAccount) {
        bankAccounts.remove(bankAccount);
    }
}

