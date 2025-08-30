package com.nurda.spring.portfoliotracker1.service;

// Stub-реализация MarketService для тестов
public class StubMarketService extends MarketService {
    private double fixedPrice = 50000.0;
    
    public StubMarketService() {
        super(); // Вызов родительского конструктора
    }
    
    public StubMarketService(double fixedPrice) {
        super();
        this.fixedPrice = fixedPrice;
    }
    
    @Override
    public double priceOf(String id) {
        return fixedPrice; // Всегда возвращает фиксированную цену
    }
    
    public void setFixedPrice(double fixedPrice) {
        this.fixedPrice = fixedPrice;
    }
}