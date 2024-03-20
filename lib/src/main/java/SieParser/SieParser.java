package SieParser;

import SieParser.entity.*;
import SieParser.parser.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class SieParser {

    private final Path file;
    private final Map<String, List<String>> linesByFlag = new HashMap<>();
    private final ArrayList<Voucher> vouchers = new ArrayList<>();

    public SieParser(Path file) throws IOException {
        this.file = file;
        readFile();
    }

    public void readFile() throws IOException {

        Set<String> flags = Set.of("#RES", "#UB", "#IB", "#RAR", "#KONTO", "#SIETYP", "#ORGNR", "#FNAMN");
        List<String> voucherLines = new ArrayList<>();
        boolean isReadingVoucher = false;

        try (Stream<String> linesStream = Files.lines(file, Charset.forName("IBM437"))) {
            List<String> lines = linesStream.map(String::strip).toList();

            for (String line : lines) {
                boolean flagFound = flags.stream().anyMatch(line::startsWith);

                if (flagFound) {
                    String flag = flags.stream().filter(line::startsWith).findFirst().orElse("");
                    String value = line.replaceFirst(Pattern.quote(flag), "").trim();
                    List<String> flagLines = linesByFlag.computeIfAbsent(flag, k -> new ArrayList<>());
                    flagLines.add(value);
                }

                if (line.startsWith("#VER")) {
                    isReadingVoucher = true;
                    voucherLines.add(line);
                }

                if (isReadingVoucher && line.startsWith("#TRANS")) {
                    voucherLines.add(line);
                }

                if (line.equals("}")) {
                    createVoucher(voucherLines);
                    voucherLines.clear();
                    isReadingVoucher = false;
                }
            }
        }
    }

    void createVoucher(List<String> lines) {
        AtomicReference<Voucher> voucher = new AtomicReference<>(new Voucher());
        lines.forEach(line -> {
            String[] parts;
            if (line.startsWith("#VER")) {
                System.out.println("Processing line: " + line);
                parts = line.split(" (?=([^\"]*\"[^\"]*\")*[^\"]*$)", 7);
                voucher.get().setVoucherSeries(parts[1].replaceAll("^\"|\"$", ""));
                voucher.get().setVoucherNumber(parts[2].replaceAll("^\"|\"$", ""));
                voucher.get().setVoucherDate(parts[3].replaceAll("^\"|\"$", ""));
                voucher.get().setVoucherDescription(parts[4].replaceAll("^\"|\"$", ""));
                voucher.get().setRegisterDate(parts[5].replaceAll("^\"|\"$", ""));
            }
            if (line.startsWith("#TRANS")) {
                Transaction transaction = new Transaction();
                parts = line.split(" ", 6);
                transaction.setAccountNumber(Integer.parseInt(parts[1]));
                transaction.setAmount(new BigDecimal(parts[3]));
                transaction.setLineDescription("");
                voucher.get().transactions.add(transaction);
            }
        });
        vouchers.add(voucher.get());
    }

    public List<Account> getAccounts() {
        Parser<Account> parser = new Konto();
        return parser.parse(linesByFlag.get(parser.getFlag()));
    }

    public List<Voucher> getVouchers() {
        return this.vouchers;
    }

    public List<FinancialYear> getFinancialYears() {
        Parser<FinancialYear> parser = new Rar();
        return parser.parse(linesByFlag.get(parser.getFlag()));
    }

    public List<Balance> getIncomeStatement() {
        Parser<Balance> parser = new Res();
        return parser.parse(linesByFlag.get(parser.getFlag()));
    }

    public List<Balance> getOpeningBalances() {
        Parser<Balance> parser = new Ib();
        return parser.parse(linesByFlag.get(parser.getFlag()));
    }

    public List<Balance> getClosingBalances() {
        Parser<Balance> parser = new Ub();
        return parser.parse(linesByFlag.get(parser.getFlag()));
    }

    public Meta getMetaData() {
        Meta meta = new Meta();

        if (linesByFlag.containsKey("#FNAMN")) {
            meta.setCompanyName(linesByFlag.get("#FNAMN").getFirst().replaceAll("^\"|\"$", ""));
        }

        if (linesByFlag.containsKey("#SIETYP")) {
            meta.setSieType(linesByFlag.get("#SIETYP").getFirst().replaceAll("^\"|\"$", ""));
        }

        if (linesByFlag.containsKey("#ORGNR")) {
            meta.setOrganizationNumber(linesByFlag.get("#ORGNR").getFirst().replaceAll("^\"|\"$", ""));
        }
        return meta;
    }
}
