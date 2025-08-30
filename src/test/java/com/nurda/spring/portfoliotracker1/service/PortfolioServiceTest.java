package com.nurda.spring.portfoliotracker1.service;

import com.nurda.spring.portfoliotracker1.dto.AddPortfolioItemRequest;
import com.nurda.spring.portfoliotracker1.dto.AggregatedAssetDto;
import com.nurda.spring.portfoliotracker1.model.PortfolioItem;
import com.nurda.spring.portfoliotracker1.repo.PortfolioItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PortfolioServiceTest {

    @Mock
    private PortfolioItemRepository portfolioItemRepository;

    private StubMarketService stubMarketService;
    private PortfolioService portfolioService;

    @BeforeEach
    void setUp() {
        stubMarketService = new StubMarketService(50000.0);
        portfolioService = new PortfolioService(portfolioItemRepository, stubMarketService);
    }

    @Test
    void testAddPortfolioItem_Success() {
        // Подготовка тестовых данных
        AddPortfolioItemRequest request = createTestRequest();

        PortfolioItem expectedItem = createTestPortfolioItem();

        // Настройка моков
        when(portfolioItemRepository.save(any(PortfolioItem.class))).thenReturn(expectedItem);

        // Выполнение метода
        PortfolioItem result = portfolioService.add(request);

        // Проверки
        assertNotNull(result);
        assertEquals("bitcoin", result.getAssetId());
        assertEquals("Bitcoin", result.getAssetName());
        assertEquals("BTC", result.getAssetSymbol());
        assertEquals(0, new BigDecimal("1.5").compareTo(result.getQuantity()));
        assertEquals(0, new BigDecimal("45000.0").compareTo(result.getPurchasePrice()));
        assertEquals(0, new BigDecimal("46000.0").compareTo(result.getCurrentPrice()));
        
        // Проверка вызова репозитория
        verify(portfolioItemRepository, times(1)).save(any(PortfolioItem.class));
    }

    @Test
    void testAddPortfolioItem_WithoutCurrentPrice() {
        // Подготовка тестовых данных (без currentPrice)
        AddPortfolioItemRequest request = createTestRequest();
        request.setCurrentPrice(null);

        PortfolioItem expectedItem = new PortfolioItem(
            "bitcoin", "Bitcoin", "BTC",
            new BigDecimal("1.5"), new BigDecimal("45000.0"),
            new BigDecimal("45000.0"), new BigDecimal("67500.0"),
            LocalDate.of(2023, 1, 15)
        );

        // Настройка моков
        when(portfolioItemRepository.save(any(PortfolioItem.class))).thenReturn(expectedItem);

        // Выполнение метода
        PortfolioItem result = portfolioService.add(request);

        // Проверки
        assertNotNull(result);
        // currentPrice должен быть равен purchasePrice
        assertEquals(0, new BigDecimal("45000.0").compareTo(result.getCurrentPrice()));
    }

    @Test
    void testAggregated_Success() {
        // Подготовка тестовых данных
        PortfolioItem item1 = createPortfolioItem("1.0", "40000.0");
        PortfolioItem item2 = createPortfolioItem("0.5", "42000.0");

        // Настройка моков
        when(portfolioItemRepository.findAll()).thenReturn(Arrays.asList(item1, item2));

        // Выполнение метода
        List<AggregatedAssetDto> result = portfolioService.aggregated();

        // Проверки
        assertNotNull(result);
        assertEquals(1, result.size());
        
        AggregatedAssetDto dto = result.get(0);
        assertEquals("bitcoin", dto.getAssetId());
        assertEquals(0, new BigDecimal("1.5").compareTo(dto.getTotalQuantity()));
        assertEquals(0, new BigDecimal("61000.0").compareTo(dto.getTotalInvestment()));
        assertEquals(0, new BigDecimal("40666.66666667").compareTo(dto.getAvgPurchasePrice()));
        assertEquals(0, new BigDecimal("50000.0").compareTo(dto.getCurrentPrice()));
        assertEquals(0, new BigDecimal("75000.0").compareTo(dto.getCurrentValue()));
        assertEquals(0, new BigDecimal("14000.0").compareTo(dto.getProfit()));
        
        // Проверка вызовов
        verify(portfolioItemRepository, times(1)).findAll();
    }

    @Test
    void testAggregated_EmptyPortfolio() {
        // Настройка моков для пустого портфеля
        when(portfolioItemRepository.findAll()).thenReturn(Arrays.asList());

        // Выполнение метода
        List<AggregatedAssetDto> result = portfolioService.aggregated();

        // Проверки
        assertNotNull(result);
        assertTrue(result.isEmpty());
        
        // Проверка вызовов
        verify(portfolioItemRepository, times(1)).findAll();
    }

    @Test
    void testRemoveAsset_Success() {
        // Настройка моков
        doNothing().when(portfolioItemRepository).deleteByAssetId("bitcoin");

        // Выполнение метода
        portfolioService.removeAsset("bitcoin");

        // Проверка вызова репозитория
        verify(portfolioItemRepository, times(1)).deleteByAssetId("bitcoin");
    }

    @Test
    void testRemoveAsset_MultipleCalls() {
        // Настройка моков
        doNothing().when(portfolioItemRepository).deleteByAssetId(anyString());

        // Многократный вызов метода
        portfolioService.removeAsset("bitcoin");
        portfolioService.removeAsset("ethereum");

        // Проверка вызовов репозитория
        verify(portfolioItemRepository, times(1)).deleteByAssetId("bitcoin");
        verify(portfolioItemRepository, times(1)).deleteByAssetId("ethereum");
        verify(portfolioItemRepository, times(2)).deleteByAssetId(anyString());
    }

    // Вспомогательные методы
    private AddPortfolioItemRequest createTestRequest() {
        AddPortfolioItemRequest request = new AddPortfolioItemRequest();
        request.setAssetId("bitcoin");
        request.setAssetName("Bitcoin");
        request.setAssetSymbol("BTC");
        request.setQuantity(new BigDecimal("1.5"));
        request.setPurchasePrice(new BigDecimal("45000.0"));
        request.setCurrentPrice(new BigDecimal("46000.0"));
        request.setAmountInvested(new BigDecimal("67500.0"));
        request.setPurchaseDate("2023-01-15");
        return request;
    }

    private PortfolioItem createTestPortfolioItem() {
        return new PortfolioItem(
            "bitcoin", "Bitcoin", "BTC",
            new BigDecimal("1.5"), new BigDecimal("45000.0"),
            new BigDecimal("46000.0"), new BigDecimal("67500.0"),
            LocalDate.of(2023, 1, 15)
        );
    }

    private PortfolioItem createPortfolioItem(String quantity, String purchasePrice) {
        return new PortfolioItem(
            "bitcoin", "Bitcoin", "BTC",
            new BigDecimal(quantity), new BigDecimal(purchasePrice),
            new BigDecimal("50000.0"), new BigDecimal("10000.0"),
            LocalDate.now()
        );
    }
}
