import SieParser.SieParser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class SieParserTest {
    @Test
    void testSieParser() throws IOException {

        File file = new File("src/test/java/test.se");
        SieParser sieParser = new SieParser(file.toPath());
        assert(!sieParser.getAccounts().isEmpty());
        assert(!sieParser.getVouchers().isEmpty());
        assert(!sieParser.getFinancialYears().isEmpty());
        assert(!sieParser.getIncomeStatement().isEmpty());
        assert(!sieParser.getOpeningBalances().isEmpty());
        assert(!sieParser.getClosingBalances().isEmpty());
        assert(sieParser.getMetaData() != null);
    }
}
