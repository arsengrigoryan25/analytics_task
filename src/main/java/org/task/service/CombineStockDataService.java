package org.task.service;

import org.springframework.stereotype.Service;
import org.task.model.Stock;
import org.task.model.StockSearchNames;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CombineStockDataService {

    private final IndeedClient indeedClient;
    private final YahooClient yahooClient;

    public CombineStockDataService(IndeedClient indeedClient, YahooClient yahooClient) {
        this.indeedClient = indeedClient;
        this.yahooClient = yahooClient;
    }

    public List<Stock> getStocks() {
        return yahooClient.extractStockSearchData().map(names -> names.stream().map(searchNames ->
                                new Stock(concatStockNames(searchNames), concatIndustryNames(searchNames)))
                        .collect(Collectors.toList()))
                .orElse(new ArrayList<>());
    }

    private String concatStockNames(StockSearchNames searchNames) {
        return String.format("%s(%s)",
                searchNames.getYahooSearchName(),
                searchNames.getIndeedSearchName().replace("+", " "));
    }

    private String concatIndustryNames(StockSearchNames searchNames) {
        return String.format("%s %s",
                yahooClient.extractStockIndustryName(searchNames.getYahooSearchName()),
                indeedClient.extractStockIndustryName(searchNames.getIndeedSearchName()));
    }
}
