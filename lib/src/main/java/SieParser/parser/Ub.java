package SieParser.parser;

import SieParser.entity.Balance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Ub extends Parser<Balance> {

    private final String FLAG = "#UB";

    @Override
    public List<Balance> parse(List<String> lines) {
        List<Balance> balances = new ArrayList<>();
        lines.forEach(line -> {
            String[] parts = line.split(" ", 4);
            String id = parts[0].replaceAll("^\"|\"$", "");
            String accountNumber = parts[1].replaceAll("^\"|\"$", "");
            String amount = parts[2].replaceAll("^\"|\"$", "");
            BigDecimal bigDecimal = new BigDecimal(amount);
            Balance balance = new Balance(
                    Integer.parseInt(id),
                    Integer.parseInt(accountNumber),
                    bigDecimal);
            balances.add(balance);
        });
        return balances;
    }

    @Override
    public String getFlag() {
        return FLAG;
    }
}
