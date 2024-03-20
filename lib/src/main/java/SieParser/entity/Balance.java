package SieParser.entity;

import java.math.BigDecimal;

public class Balance extends Entity {

    private int financialYear;
    private int accountNumber;
    private BigDecimal amount;

    public Balance(int financialYear, int accountNumber, BigDecimal amount) {
        this.financialYear = financialYear;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public int getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(int financialYear) {
        this.financialYear = financialYear;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
