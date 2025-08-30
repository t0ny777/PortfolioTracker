/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nurda.spring.portfoliotracker1.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Asset {
    private String id;
    private String symbol;
    private String name;
    
    @JsonProperty("current_price")
    private double price;
    
    @JsonProperty("price_change_percentage_24h")
    private double change;
    
        // Добавляем поле для изображения
    @JsonProperty("image")
    private String imageUrl;

    // Геттеры и сеттеры для нового поля
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    // Геттеры и сеттеры
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public double getChange() { return change; }
    public void setChange(double change) { this.change = change; }
}

