package SieParser.parser;

import SieParser.entity.Entity;

import java.util.List;

public abstract class Parser<T extends Entity> {
    public abstract List<T> parse(List<String> lines);

    public abstract String getFlag();
}