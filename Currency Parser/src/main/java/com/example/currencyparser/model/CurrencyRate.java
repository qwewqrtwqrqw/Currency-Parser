package com.example.currencyparser.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "currency_rate")
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_name")
    private String currencyName;

    @Column(name = "rate_to_rub", nullable = false)
    private double rateToRub;

    @Column(name = "rate_to_usd", nullable = false)
    private double rateToUsd;

    @Column(name = "change_percentage", nullable = false)
    private double changePercentage;

    @Column(name = "date")
    private LocalDate date;

    // ====== Геттеры и сеттеры ======

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCurrencyName() { return currencyName; }
    public void setCurrencyName(String currencyName) { this.currencyName = currencyName; }

    public double getRateToRub() { return rateToRub; }
    public void setRateToRub(double rateToRub) { this.rateToRub = rateToRub; }

    public double getRateToUsd() { return rateToUsd; }
    public void setRateToUsd(double rateToUsd) { this.rateToUsd = rateToUsd; }

    public double getChangePercentage() { return changePercentage; }
    public void setChangePercentage(double changePercentage) { this.changePercentage = changePercentage; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
