package SieParser.entity;

public class Account extends Entity {

    private int accountNumber;

    private String accountDescription;

    public Account(int accountNumber, String accountDescription) {
        this.accountNumber = accountNumber;
        this.accountDescription = accountDescription;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }
}