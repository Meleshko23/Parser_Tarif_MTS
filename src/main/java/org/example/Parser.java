package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

public class MTSTariffParser {
    public static List<Tariff> parseMTSTariffs(String htmlContent) {
        List<Tariff> tariffs = new ArrayList<>();
        Document document = Jsoup.parse(htmlContent);
        Elements tariffElements = document.select(".card.card__wrapper");

        for (Element tariffElement : tariffElements) {
            String name = tariffElement.select(".card-title.card-title__margin").text();
            String description = tariffElement.select(".card-description").text();
            String price = tariffElement.select(".price-main .price-text").text();

            tariffs.add(new Tariff(name, description, price));
        }

        return tariffs;
    }
}
