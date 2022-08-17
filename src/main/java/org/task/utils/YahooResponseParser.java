package org.task.utils;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.task.model.StockSearchNames;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class YahooResponseParser {

    private static final String STOCK = "tr[class*=simpTblRow]";
    private static final String NAME = "td[aria-label=Name]";
    private static final String SYMBOL = "a[data-test*=quoteLink]";
    private static final String SECTION_CONTAINING_INDUSTRY = "p[class*=D(ib) Va(t)]";
    private static final String SPAN = "span";

    private YahooResponseParser() {
    }

    public static Optional<List<StockSearchNames>> extractStockSearchData(Document document) {
        Elements section = document.select(STOCK);
        if (section.isEmpty()) return Optional.empty();
        return getStockSearchNames(section);
    }

    public static Optional<String> extractStockIndustryName(Document document) {
        Elements section = document.select(SECTION_CONTAINING_INDUSTRY);
        if (section.isEmpty()) return Optional.empty();
        return Optional.of(section.select(SPAN).get(3).text());
    }

    private static Optional<List<StockSearchNames>> getStockSearchNames(Elements section) {
        return Optional.of(
                section.stream()
                        .map(stock ->
                                new StockSearchNames(processNameForBetterSearching(stock.select(NAME).text()), stock.select(SYMBOL).text()))
                        .collect(Collectors.toList()));
    }

    private static String processNameForBetterSearching(String name) {
        name = name.replace(" Inc.", "");
        name = name.replace(",", "");
        name = name.replace("Holdings", "");
        name = name.replace("Holding", "");
        name = name.replace("Technologies", "");
        name = name.replace("Limited", "");
        name = name.replace("plc", "");
        name = name.replace("&", "");
        name = name.replace("Ltd.", "");
        name = name.replace("Group", "");
        name = name.replace("Platforms", "");
        name = name.replace("Brands", "");
        name = name.trim();
        name = name.replace(" ", "+");
        name = name.replace("++", "+");
        return name;
    }
}
