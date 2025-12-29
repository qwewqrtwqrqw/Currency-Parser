package com.example.currencyparser.service;

import com.example.currencyparser.model.CurrencyRate;
import com.example.currencyparser.repository.CurrencyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CurrencyStorageService {

    private final CurrencyRepository repository;
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    public CurrencyStorageService(CurrencyRepository repository) {
        this.repository = repository;
    }

    public void saveCurrencyAsync(CurrencyRate rate) {
        if (rate != null) {
            executor.submit(() -> repository.save(rate));
        }
    }

    public List<CurrencyRate> getAllCurrencies() {
        return repository.findAll();
    }
}
