package com.nurda.spring.portfoliotracker1.service;
import com.nurda.spring.portfoliotracker1.dto.MarketAssetDto;
import com.nurda.spring.portfoliotracker1.model.Asset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;



@SpringBootTest
@Tag("integration")
class MarketServiceTest {

    @Autowired
    private MarketService marketService;

    @Test
    void testPriceOf_BTC() {
        // В CoinGecko id — "bitcoin" (не "BTC")
        double price = marketService.priceOf("bitcoin");
        assertTrue(price > 0, "Цена BTC должна быть > 0");
    }

    @Test
    void testPriceOf_ETH() {
        double price = marketService.priceOf("ethereum");
        assertTrue(price > 0, "Цена ETH должна быть > 0");
    }

    @Test
    void testPriceOf_SOL() {
        double price = marketService.priceOf("solana");
        assertTrue(price > 0, "Цена SOL должна быть > 0");
    }

    @Test
    void testTopCoins() {
        int n = 5;
        List<MarketAssetDto> top = marketService.top(n);

        assertNotNull(top, "Список не должен быть null");
        assertEquals(n, top.size(), "Должно вернуться ровно " + n + " монет");

        // Если MarketAssetDto — класс с геттерами:
        assertTrue(top.stream().allMatch(a -> a != null && a.price > 0),
                "У всех монет цена должна быть > 0");

        // Если у тебя MarketAssetDto — record, замени строку выше на:
        // assertTrue(top.stream().allMatch(a -> a != null && a.price() > 0), "...");
    }
}
