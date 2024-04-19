# jsieparser
Jsieparser is a Java library designed to read SIE4 files commonly used in accounting software in Sweden, identified by
the .se extension. With jsieparser, you can effortlessly parse SIE files, extract relevant data, and integrate it into your projects.

## Usage
To get started, simply download the latest release of jsieparser and incorporate it into your project. You can either
manually add it or import it via Maven or Gradle from GitHub Packages. Initialize a new parser by invoking the SieParser
class with the file path as an argument.

    SieParser sieParser = new SieParser(Path file);

## Structure

### Parser
In a SIE4 file, there are flags indicating the beginning of specific entities. Each flag has its own parsing rules, and
jsieparser provides implementations for these rules. Each flag corresponds to a specific entity, and parsing is performed
based on these entities.

### Entity
The base class Entity acts as a foundation for all entities parsed from the SIE4 file. For example, a #RES flag in the
SIE4 file represents a balance entity. The corresponding POJO (Plain Old Java Object) for this entity would include
properties such as financial year, account number, and amount.

Other entity should represent the actual string from the SIE4 file. For example a #RES flag in the raw file would look
something like this:

``` text
#RES -1 3000 -24992.00
```

This would correspond to a Balance entity (POJO) with the following properties:

    private int financialYear;  
    private int accountNumber;  
    private BigDecimal amount;

## Features

The following list of methods can be called on SieParser to extract data:

- getAccounts();
- getVouchers();
- getFinancialYears();
- getIncomeStatement();
- getOpeningBalances();
- getClosingBalances();

These methods corresponds to the flags found within SIE files, providing a convenient way to access specific data.

## Development

Next feature is to implement a SIE file writer.
