package com.remote100Test.dto;

import com.remote100Test.model.TickerModel;

import java.util.List;

public class TickerDto {
    private List<TickerModel> tickerModels;

    private String currencyPair;
    private int dailyPercentage;
    private int weeklyPercentage;
    private int monthlyPercentage;

    private double currentSellPrice;
    private double currentBuyPrice;
    private double currentRate;

    public TickerDto() {
    }

    public TickerDto(List<TickerModel> tickerModels) {
        this.tickerModels = tickerModels;
    }

    public List<TickerModel> getTickerModels() {
        return tickerModels;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public void setTickerModels(List<TickerModel> tickerModels) {
        this.tickerModels = tickerModels;
    }

    public int getDailyPercentage() {
        return dailyPercentage;
    }

    public void setDailyPercentage(int dailyPercentage) {
        this.dailyPercentage = dailyPercentage;
    }

    public int getWeeklyPercentage() {
        return weeklyPercentage;
    }

    public void setWeeklyPercentage(int weeklyPercentage) {
        this.weeklyPercentage = weeklyPercentage;
    }

    public int getMonthlyPercentage() {
        return monthlyPercentage;
    }

    public void setMonthlyPercentage(int monthlyPercentage) {
        this.monthlyPercentage = monthlyPercentage;
    }

    public double getCurrentSellPrice() {
        return currentSellPrice;
    }

    public void setCurrentSellPrice(double currentSellPrice) {
        this.currentSellPrice = currentSellPrice;
    }

    public double getCurrentBuyPrice() {
        return currentBuyPrice;
    }

    public void setCurrentBuyPrice(double currentBuyPrice) {
        this.currentBuyPrice = currentBuyPrice;
    }

    public double getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(double currentRate) {
        this.currentRate = currentRate;
    }

    @Override
    public String toString() {
        return "TickerDto{" +
                "tickerModels=" + tickerModels +
                ", dailyPercentage=" + dailyPercentage +
                ", weeklyPercentage=" + weeklyPercentage +
                ", monthlyPercentage=" + monthlyPercentage +
                '}';
    }
}
