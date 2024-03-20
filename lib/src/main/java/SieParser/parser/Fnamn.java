package SieParser.parser;

import SieParser.entity.CompanyName;

import java.util.List;

public class Fnamn extends Parser<CompanyName> {

    private final String FLAG = "#FNAMN";

    @Override
    public List<CompanyName> parse(List<String> lines) {
        return null;
    }

    @Override
    public String getFlag() {
        return FLAG;
    }
}
