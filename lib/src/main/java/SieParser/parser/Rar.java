package SieParser.parser;

import SieParser.entity.FinancialYear;

import java.util.ArrayList;
import java.util.List;

public class Rar extends Parser<FinancialYear> {

    public final String FLAG = "#RAR";

    public List<FinancialYear> parse(List<String> lines) {
        List<FinancialYear> financialYears = new ArrayList<>();
        lines.forEach(line -> {
            String[] parts = line.split(" ", 3);
            String id = parts[0].replaceAll("^\"|\"$", "");
            String starts = parts[1].replaceAll("^\"|\"$", "");
            String ends = parts[2].replaceAll("^\"|\"$", "");
            FinancialYear financialYear = new FinancialYear(Integer.parseInt(id), starts, ends);
            financialYears.add(financialYear);
        });
        return financialYears;
    }

    @Override
    public String getFlag() {
        return FLAG;
    }
}
