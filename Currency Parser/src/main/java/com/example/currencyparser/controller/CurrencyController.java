package com.example.currencyparser.controller;

import com.example.currencyparser.model.CurrencyRate;
import com.example.currencyparser.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/answer")
public class CurrencyController {

    @Autowired
    private CurrencyRepository repository;

    /**
     * Получение курсов с возможностью фильтрации по валюте и дате.
     * Примеры запросов:
     * GET /answer?currency=USD
     * GET /answer?date=2025-12-29
     * GET /answer?currency=EUR&date=2025-12-29
     */
    @GetMapping
    public List<CurrencyRate> getRates(
            @RequestParam(required = false) String currency,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        List<CurrencyRate> allRates = repository.findAll();

        return allRates.parallelStream()
                .filter(rate -> currency == null || rate.getCurrencyName().equalsIgnoreCase(currency))
                .filter(rate -> date == null || rate.getDate().equals(date))
                .sorted((r1, r2) -> r1.getCurrencyName().compareToIgnoreCase(r2.getCurrencyName()))
                .collect(Collectors.toList());
    }
}

