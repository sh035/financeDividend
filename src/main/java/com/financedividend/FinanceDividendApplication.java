package com.financedividend;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

//@SpringBootApplication
public class FinanceDividendApplication {

	public static void main(String[] args) {
//		SpringApplication.run(FinanceDividendApplication.class, args);

		try {
			Connection connection = Jsoup.connect("https://finance.yahoo.com/quote/O/history?period1=1618204284&period2=1649740284&interval=1mo&filter=history&frequency=1mo&includeAdjustedClose=true")
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
			Document document = connection.get();

			Elements eles = document.getElementsByClass("table");
			Element ele = eles.get(0);	// table 전체

			// thead 가지고 오고 싶으면 get(0), tbody는 get(1) , tfoot은 get(2)
			Element tbody = ele.children().get(1);
			for (Element e : tbody.children()) {
				String txt = e.text();
				if (!txt.endsWith("Dividend")) {
					continue;
				}

				String[] splits = txt.split(" ");
				String month = splits[0];
				int day = Integer.valueOf(splits[1].replace(",", ""));
				int year = Integer.valueOf(splits[2]);
				String dividend = splits[3];

				System.out.println(year + "/" + month + "/" + day + " -> " + dividend);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
