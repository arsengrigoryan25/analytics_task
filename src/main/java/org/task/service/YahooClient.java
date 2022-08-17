package org.task.service;

import org.springframework.stereotype.Service;
import org.task.model.StockSearchNames;
import org.task.utils.YahooResponseParser;

import java.util.List;
import java.util.Optional;

@Service
public class YahooClient {

    private static final String FIFTY_RANDOM_STOCKS_URL = "https://finance.yahoo.com/most-active/?offset=0&count=50";
    private static final String STOCK_BY_NAME_URL = "https://finance.yahoo.com/quote/%s/profile";
    private final ResponseParser responseParser;

    public YahooClient(ResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public Optional<List<StockSearchNames>> extractStockSearchData() {
        return YahooResponseParser.extractStockSearchData(responseParser.parse(FIFTY_RANDOM_STOCKS_URL));
    }

    public String extractStockIndustryName(String name) {
        return YahooResponseParser.extractStockIndustryName(responseParser.parse(String.format(STOCK_BY_NAME_URL, name))).orElse("");
    }
}
