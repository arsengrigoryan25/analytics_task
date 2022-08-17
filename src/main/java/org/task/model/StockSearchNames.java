package org.task.model;

public class StockSearchNames {
    private final String indeedSearchName;
    private final String yahooSearchName;

    public StockSearchNames(String indeedSearchName, String yahooSearchName) {
        this.indeedSearchName = indeedSearchName;
        this.yahooSearchName = yahooSearchName;
    }

    public String getIndeedSearchName() {
        return indeedSearchName;
    }

    public String getYahooSearchName() {
        return yahooSearchName;
    }
}
