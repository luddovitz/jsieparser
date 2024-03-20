package SieParser.parser;

import SieParser.entity.Balance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Res extends Parser<Balance> {

    public final String FLAG = "#RES";

    @Override
    public List<Balance> parse(List<String> lines) {
        List<Balance> resultSet = new ArrayList<>();
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
            resultSet.add(balance);
        });
        return resultSet;
    }

    @Override
    public String getFlag() {
        return FLAG;
    }
}
