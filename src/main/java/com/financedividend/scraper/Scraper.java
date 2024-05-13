package com.financedividend.scraper;

import com.financedividend.model.Company;
import com.financedividend.model.ScrapedResult;

public interface Scraper {
    Company scrapCompanyByTicker(String ticker);

    ScrapedResult scrap(Company company);
}
