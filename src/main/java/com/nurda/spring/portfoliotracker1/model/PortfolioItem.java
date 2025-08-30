/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nurda.spring.portfoliotracker1.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "portfolio_items")
public class PortfolioItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // CoinGecko id (например "bitcoin")
    @Column(name = "asset_id", nullable = false)
    private String assetId;

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Column(name = "asset_symbol", nullable = false)
    private String assetSymbol;

    @Column(nullable = false, precision = 30, scale = 12)
    private BigDecimal quantity;

    @Column(name = "purchase_price", nullable = false, precision = 30, scale = 8)
    private BigDecimal purchasePrice;

    @Column(name= "current_price", precision = 30, scale= 8, nullable = true)
    private BigDecimal currentPrice;
    
    @Column(name= "amount_invested", precision = 30, scale = 12)
    private BigDecimal amountInvested;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

   public PortfolioItem() {}

    public PortfolioItem(String assetId, String assetName, String assetSymbol,
                         BigDecimal quantity, BigDecimal purchasePrice,
                         BigDecimal currentPrice, BigDecimal amountInvested, LocalDate purchaseDate) {
        this.assetId = assetId;
        this.assetName = assetName;
        this.assetSymbol = assetSymbol;
        this.quantity = quantity;
        this.purchasePrice = purchasePrice;
        this.currentPrice = currentPrice;
        this.amountInvested = amountInvested;
        this.purchaseDate = purchaseDate;
    }

    // getters/setters

    public Long getId() { return id; }
    public String getAssetId() { return assetId; }
    public void setAssetId(String assetId) { this.assetId = assetId; }
    public String getAssetName() { return assetName; }
    public void setAssetName(String assetName) { this.assetName = assetName; }
    public String getAssetSymbol() { return assetSymbol; }
    public void setAssetSymbol(String assetSymbol) { this.assetSymbol = assetSymbol; }
    public BigDecimal getQuantity() { return quantity; }
    public void setQuantity(BigDecimal quantity) { this.quantity = quantity; }
    public BigDecimal getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(BigDecimal purchasePrice) { this.purchasePrice = purchasePrice; }
    public BigDecimal getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(BigDecimal currentPrice) { this.currentPrice = currentPrice; }
    public BigDecimal getAmountInvested() { return amountInvested; }
    public void setAmountInvested(BigDecimal amountInvested) { this.amountInvested = amountInvested; }
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
}
