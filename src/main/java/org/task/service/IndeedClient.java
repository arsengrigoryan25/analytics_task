package org.task.service;

import org.springframework.stereotype.Service;
import org.task.utils.IndeedResponseParser;
import java.util.Optional;

@Service
public class IndeedClient {

    private static final String SEARCH_STOCK_BY_NAME_URL = "https://www.indeed.com/companies/search?q=%s";
    private static final String STOCK_BY_LINK = "https://www.indeed.com%s";
    private final ResponseParser responseParser;

    public IndeedClient(ResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public String extractStockIndustryName(String name) {
        Optional<String> industryName = getIndustryName(name);
        return industryName.orElse("");
    }

    private Optional<String> getIndustryName(String name) {
        return getStockLink(name)
                .flatMap(
                        link ->
                                IndeedResponseParser.extractStockIndustryName(
                                        responseParser.parse(String.format(STOCK_BY_LINK, link))));
    }

    private Optional<String> getStockLink(String name) {
        return IndeedResponseParser.extractStockProfileLink(
                responseParser.parse(String.format(SEARCH_STOCK_BY_NAME_URL, name)));
    }
}
