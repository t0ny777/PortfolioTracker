package com.nurda.spring.portfoliotracker1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class AddPortfolioItemRequest {
    @NotBlank
    private String assetId; // Переименуйте symbol в assetId
    
    @NotBlank
    private String assetName;
    
    @NotBlank
    private String assetSymbol;
    
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal quantity;
    
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal purchasePrice;
    
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal currentPrice;
    
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amountInvested;
    
    @NotBlank
    private String purchaseDate;

    // Правильные геттеры и сеттеры
    public String getAssetId() {
        return assetId;
    }
    
    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }
    
    public String getAssetName() {
        return assetName;
    }
    
    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }
    
    public String getAssetSymbol() {
        return assetSymbol;
    }
    
    public void setAssetSymbol(String assetSymbol) {
        this.assetSymbol = assetSymbol;
    }
    
    public BigDecimal getQuantity() {
        return quantity;
    }
    
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
    
    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }
    
    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }
    
    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }
    
    public BigDecimal getAmountInvested() {
        return amountInvested;
    }
    
    public void setAmountInvested(BigDecimal amountInvested) {
        this.amountInvested = amountInvested;
    }
    
    public String getPurchaseDate() {
        return purchaseDate;
    }
    
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}