package org.task.model;

public class Stock {
    private final String name;
    private final String industry;

    public Stock(String name, String industry) {
        this.name = name;
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public String getIndustry() {
        return industry;
    }
}
