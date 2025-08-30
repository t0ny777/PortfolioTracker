package com.nurda.spring.portfoliotracker1.service;

import com.nurda.spring.portfoliotracker1.dto.AggregatedAssetDto;
import com.nurda.spring.portfoliotracker1.dto.AddPortfolioItemRequest;
import com.nurda.spring.portfoliotracker1.model.PortfolioItem;
import com.nurda.spring.portfoliotracker1.repo.PortfolioItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PortfolioService {
    private final PortfolioItemRepository repo;
    private final MarketService market;

    public PortfolioService(PortfolioItemRepository repo, MarketService market) {
        this.repo = repo;
        this.market = market;
        System.out.println("PortfolioService initialized"); // Простое логирование
    }

    @Transactional
    public PortfolioItem add(AddPortfolioItemRequest req) {
        try {
            System.out.println("Adding portfolio item: " + req.getAssetId());
            
            BigDecimal quantity = req.getQuantity().setScale(12, RoundingMode.HALF_UP);
            BigDecimal purchasePrice = req.getPurchasePrice().setScale(8, RoundingMode.HALF_UP);
            
            // Обработка currentPrice - если null, используем purchasePrice
            BigDecimal currentPrice = req.getCurrentPrice();
            if (currentPrice == null) {
                currentPrice = purchasePrice;
                System.out.println("Current price not provided, using purchase price: " + currentPrice);
            }
            currentPrice = currentPrice.setScale(8, RoundingMode.HALF_UP);
            
            BigDecimal amountInvested = req.getAmountInvested().setScale(12, RoundingMode.HALF_UP);
            
            // Парсим дату
            LocalDate purchaseDate;
            try {
                purchaseDate = LocalDate.parse(req.getPurchaseDate());
            } catch (Exception e) {
                System.out.println("Invalid date format, using current date");
                purchaseDate = LocalDate.now();
            }

            PortfolioItem item = new PortfolioItem(
                req.getAssetId(),
                req.getAssetName(),
                req.getAssetSymbol(),
                quantity,
                purchasePrice,
                currentPrice,
                amountInvested,
                purchaseDate
            );
            
            PortfolioItem savedItem = repo.save(item);
            System.out.println("Item saved successfully with ID: " + savedItem.getId());
            return savedItem;
            
        } catch (Exception e) {
            System.out.println("ERROR adding portfolio item: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to add asset: " + e.getMessage(), e);
        }
    }

  

public List<AggregatedAssetDto> aggregated() {
    List<PortfolioItem> all = repo.findAll();
    Map<String, List<PortfolioItem>> byAsset = all.stream()
            .collect(Collectors.groupingBy(PortfolioItem::getAssetId));

    List<AggregatedAssetDto> out = new ArrayList<>();

    for (var entry : byAsset.entrySet()) {
        String assetId = entry.getKey();
        List<PortfolioItem> items = entry.getValue();

        BigDecimal totalQty = items.stream()
                .map(PortfolioItem::getQuantity)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // ИСПРАВЛЕНИЕ: Правильно рассчитываем общие инвестиции
        BigDecimal totalInv = items.stream()
                .map(item -> item.getPurchasePrice().multiply(item.getQuantity()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // ИСПРАВЛЕНИЕ: Правильно рассчитываем среднюю цену покупки
        BigDecimal avgPrice = totalQty.compareTo(BigDecimal.ZERO) == 0
                ? BigDecimal.ZERO
                : totalInv.divide(totalQty, 8, RoundingMode.HALF_UP);

        Double curPriceD = market.priceOf(assetId);
        BigDecimal curPrice = BigDecimal.valueOf(curPriceD == null ? 0.0 : curPriceD)
                .setScale(8, RoundingMode.HALF_UP);

        BigDecimal curValue = totalQty.multiply(curPrice).setScale(8, RoundingMode.HALF_UP);
        BigDecimal profit = curValue.subtract(totalInv).setScale(8, RoundingMode.HALF_UP);
        BigDecimal roi = totalInv.compareTo(BigDecimal.ZERO) == 0
                ? BigDecimal.ZERO
                : profit.divide(totalInv, 6, RoundingMode.HALF_UP)
                        .multiply(BigDecimal.valueOf(100));

        AggregatedAssetDto dto = new AggregatedAssetDto();
        dto.setAssetId(assetId);
        dto.setAssetName(items.get(0).getAssetName());
        dto.setAssetSymbol(items.get(0).getAssetSymbol());
        dto.setTotalQuantity(totalQty);
        dto.setTotalInvestment(totalInv); // Исправленное значение
        dto.setAvgPurchasePrice(avgPrice);
        dto.setCurrentPrice(curPrice);
        dto.setCurrentValue(curValue);
        dto.setProfit(profit);
        dto.setRoiPercent(roi);

        out.add(dto);
    }

    out.sort((a, b) -> b.getCurrentValue().compareTo(a.getCurrentValue()));
    return out;
}

    @Transactional
    public void removeAsset(String assetId) {
        repo.deleteByAssetId(assetId);
    }
}


