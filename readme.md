# jsieparser
Java implementation of a SIE4 file reader for accounting software files in Sweden with .se extension.

## Usage
Download the latest release and drop it into your project or import it using maven or Gradle from GitHub Packages.
Initialize a new parser by calling the SieParser class with file as argument.

    SieParser sieParser = new SieParser(Path file);

## Structure

**Parser**
In a SIE4 file there is something called flags, which indicates the beginning of a certain entity. There are some various parsing rules for how these strings should be handled, so each flag has gotten their own implementation of `parse` method of a default Parser class.

A parser will return a list of corresponding pojo of that specific flag object. So using for example Ub parse it will return a List of balance, which is found under entity.

**Entity**
Has an empty base call called Entity which all other extend. This makes it possibly to type the parser beeing using through the abstract base class of Parser.

Other entity should represent the actual string from the SIE4 file, so for example from a #RES string in the file.

#RES -1 3000 -24992.00

Looking at the above this would be a Balance entity (POJO) that contains the following properties:

    private int financialYear;  
    private int accountNumber;  
    private BigDecimal amount;

This is the implementation, but as you will see the methods inside the SieParser class takes care of this for you and will simply parse the file and return the requested data.

## Features

The following list of methods can be called on SieParser:

- getAccounts();
- getVouchers();
- getFinancialYears();
- getIncomeStatement();
- getOpeningBalances();
- getClosingBalances();

This corresponds to the following flags inside the SIE file:

- #RES
- #UB
- #IB
- #RAR
- #KONTO
- #TRANS
- #VER

## Development

Next step is to implement a way to write an SIE file from POJO.
