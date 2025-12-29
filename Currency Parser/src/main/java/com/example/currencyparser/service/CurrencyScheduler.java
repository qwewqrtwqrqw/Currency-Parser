package com.example.currencyparser.scheduler;

import com.example.currencyparser.service.CurrencyFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CurrencyScheduler {

    @Autowired
    private CurrencyFetchService fetchService;

    // Запуск каждые 1 час (можно настроить по своему)
    @Scheduled(fixedRate = 60 * 60 * 1000)
    public void fetchCurrenciesScheduled() {
        // Вызываем метод fetchCurrency только для фиатных валют
        fetchService.fetchCurrency("USD");
        fetchService.fetchCurrency("EUR");
        fetchService.fetchCurrency("JPY");

        System.out.println("Сбор курсов завершен.");
    }
}

