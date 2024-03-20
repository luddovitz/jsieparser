package SieParser.parser;

import SieParser.entity.Account;

import java.util.ArrayList;
import java.util.List;

public class Konto extends Parser<Account> {

    public final String FLAG = "#KONTO";

    public List<Account> parse(List<String> lines) {
        List<Account> accountSet = new ArrayList<>();
        lines.forEach(line -> {
            String[] parts = line.split(" ", 2);
            String accountNumber = parts[0];
            String accountDescription = parts[1].replaceAll("^\"|\"$", "");
            Account account = new Account(Integer.parseInt((accountNumber)), accountDescription);
            accountSet.add(account);
        });
        return accountSet;
    }

    @Override
    public String getFlag() {
        return FLAG;
    }
}
