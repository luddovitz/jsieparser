package SieParser.entity;

import java.util.ArrayList;
import java.util.List;

public class Voucher extends Entity {

    public String voucherNumber;
    public String voucherSeries;
    public String voucherDate;
    public String voucherDescription;
    public String registerDate;
    public List<Transaction> transactions = new ArrayList<>();

    public Voucher() {}

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getVoucherSeries() {
        return voucherSeries;
    }

    public void setVoucherSeries(String voucherSeries) {
        this.voucherSeries = voucherSeries;
    }

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getVoucherDescription() {
        return voucherDescription;
    }

    public void setVoucherDescription(String voucherDescription) {
        this.voucherDescription = voucherDescription;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
