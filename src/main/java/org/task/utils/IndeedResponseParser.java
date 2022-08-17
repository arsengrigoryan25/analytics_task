package org.task.utils;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Optional;

public class IndeedResponseParser {

    private static final String SECTION_CONTAINING_STOCK_LINK = "div[class*=css-11ksq3 eu4oa1w0]";
    private static final String A_TAG = "a";
    private static final String HREF = "href";
    private static final String INDUSTRY_SECTION = "li[data-testid=companyInfo-industry]";
    private static final String INDUSTRY_NAME = "div[class*=css-1w0iwyp e1wnkr790]";

    private IndeedResponseParser() {
    }

    public static Optional<String> extractStockProfileLink(Document document) {
        Elements section = document.select(SECTION_CONTAINING_STOCK_LINK);
        if (section.isEmpty()) return Optional.empty();
        return Optional.of(
                document.select(SECTION_CONTAINING_STOCK_LINK).select(A_TAG).get(0).attr(HREF));
    }

    public static Optional<String> extractStockIndustryName(Document document) {
        return Optional.of(document.select(INDUSTRY_SECTION).select(INDUSTRY_NAME).text());
    }
}
