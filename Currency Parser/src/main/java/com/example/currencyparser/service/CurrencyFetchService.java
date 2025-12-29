package com.example.currencyparser.service;

import com.example.currencyparser.model.CurrencyRate;
import com.example.currencyparser.repository.CurrencyRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class CurrencyFetchService {

    @Autowired
    private CurrencyRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();

    // Метод для получения курса конкретной валюты
    public void fetchCurrency(String currencyCode) {
        try {
            String url = "https://open.er-api.com/v6/latest/USD"; // базовая валюта USD
            String response = restTemplate.getForObject(url, String.class);
            JSONObject json = new JSONObject(response);

            JSONObject rates = json.getJSONObject("rates");
            if (!rates.has(currencyCode)) {
                System.out.println("Нет курса для " + currencyCode);
                return;
            }

            double rateToUsd = 1.0;
            double rateToRub = rates.getDouble("RUB") / rates.getDouble(currencyCode); // пример конверсии
            double changePercentage = 0; // пока можно 0 или добавить логику

            CurrencyRate currencyRate = new CurrencyRate();
            currencyRate.setCurrencyName(currencyCode);
            currencyRate.setRateToUsd(rateToUsd);
            currencyRate.setRateToRub(rateToRub);
            currencyRate.setChangePercentage(changePercentage);
            currencyRate.setDate(LocalDate.now());

            repository.save(currencyRate);
            System.out.println("Курс " + currencyCode + " сохранён");

        } catch (Exception e) {
            System.out.println("Ошибка при получении курса для " + currencyCode + ": " + e.getMessage());
        }
    }
}

