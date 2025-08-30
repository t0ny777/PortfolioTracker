/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nurda.spring.portfoliotracker1.dto;

import java.math.BigDecimal;


public class AggregatedAssetDto {
    private String assetId;
    private String assetName;
    private String assetSymbol;
    private BigDecimal totalQuantity;
    private BigDecimal totalInvestment; // Общая сумма инвестиций в этот актив
    private BigDecimal avgPurchasePrice; // Средняя цена покупки
    
    // текущие рыночные данные
    private BigDecimal currentPrice;
    private BigDecimal currentValue;
    private BigDecimal profit;
    private BigDecimal roiPercent;

    public AggregatedAssetDto() {}

    // getters/setters
    public String getAssetId() { return assetId; }
    public void setAssetId(String assetId) { this.assetId = assetId; }
    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }
    public String getAssetSymbol() { return assetSymbol; }
    public void setAssetSymbol(String assetSymbol) { this.assetSymbol = assetSymbol; }
    public BigDecimal getTotalQuantity() { return totalQuantity; }
    public void setTotalQuantity(BigDecimal totalQuantity) { this.totalQuantity = totalQuantity; }
    public BigDecimal getTotalInvestment() { return totalInvestment; }
    public void setTotalInvestment(BigDecimal totalInvestment) { this.totalInvestment = totalInvestment; }
    public BigDecimal getAvgPurchasePrice() { return avgPurchasePrice; }
    public void setAvgPurchasePrice(BigDecimal avgPurchasePrice) { this.avgPurchasePrice = avgPurchasePrice; }
    public BigDecimal getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(BigDecimal currentPrice) { this.currentPrice = currentPrice; }
    public BigDecimal getCurrentValue() { return currentValue; }
    public void setCurrentValue(BigDecimal currentValue) { this.currentValue = currentValue; }
    public BigDecimal getProfit() { return profit; }
    public void setProfit(BigDecimal profit) { this.profit = profit; }
    public BigDecimal getRoiPercent() { return roiPercent; }
    public void setRoiPercent(BigDecimal roiPercent) { this.roiPercent = roiPercent; }
    
    
}
