/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nurda.spring.portfoliotracker1.service;
import com.nurda.spring.portfoliotracker1.dto.MarketAssetDto;
import com.nurda.spring.portfoliotracker1.model.Asset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;



@Service
public class MarketService {

    private static final String COINGECKO = "https://api.coingecko.com/api/v3";
    private static final String VS = "usd";

    private final RestClient http = RestClient.create();

    public List<MarketAssetDto> top(int limit) {
        String url = COINGECKO + "/coins/markets?vs_currency=" + VS +
                "&order=market_cap_desc&per_page=" + limit + "&page=1&sparkline=false&price_change_percentage=24h";

        ResponseEntity<List> resp = http.get().uri(url)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(List.class);

        List<Map<String,Object>> raw = resp.getBody();
        if (raw == null) return List.of();

        return raw.stream().map(m -> {
            MarketAssetDto dto = new MarketAssetDto();
            dto.id = asString(m.get("id"));
            dto.name = asString(m.get("name"));
            dto.symbol = asString(m.get("symbol")).toUpperCase();
            dto.rank = asInt(m.get("market_cap_rank"));
            dto.price = asDouble(m.get("current_price"));
            dto.change24h = asDouble(m.getOrDefault("price_change_percentage_24h", 0.0));
            dto.marketCap = asDouble(m.get("market_cap"));
            dto.image = asString(m.get("image"));
            return dto;
        }).collect(Collectors.toList());
    }

public double priceOf(String id) {
    try {
        String url = COINGECKO + "/coins/markets?vs_currency=" + VS + "&ids=" + id;
        
        // Добавляем таймаут и повторные попытки
        ResponseEntity<List> resp = http.get().uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(List.class);
        
        List<Map<String,Object>> raw = resp.getBody();
        if (raw == null || raw.isEmpty()) {
            System.out.println("No data found for asset: " + id);
            return 0.0;
        }
        
        Double price = asDouble(raw.get(0).get("current_price"));
        if (price == null || price == 0.0) {
            // Если цена не получена, пробуем альтернативный endpoint
            return priceFromAlternativeEndpoint(id);
        }
        
        return price;
    } catch (Exception e) {
        System.out.println("Error fetching price for " + id + ": " + e.getMessage());
        return priceFromAlternativeEndpoint(id);
    }
}

private double priceFromAlternativeEndpoint(String id) {
    try {
        // Альтернативный endpoint для получения цены
        String url = COINGECKO + "/simple/price?ids=" + id + "&vs_currencies=" + VS;
        ResponseEntity<Map> resp = http.get().uri(url)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .toEntity(Map.class);
        
        Map<String, Map<String, Object>> data = resp.getBody();
        if (data != null && data.containsKey(id)) {
            Map<String, Object> assetData = data.get(id);
            return asDouble(assetData.get(VS));
        }
    } catch (Exception e) {
        System.out.println("Alternative endpoint also failed for " + id + ": " + e.getMessage());
    }
    return 0.0;
}

    // helpers
    private static String asString(Object o){ return o==null? null : o.toString(); }
    private static Integer asInt(Object o){
        if (o==null) return null;
        if (o instanceof Number n) return n.intValue();
        try { return Integer.parseInt(o.toString()); } catch (Exception e){ return null; }
    }
    private static Double asDouble(Object o){
        if (o==null) return null;
        if (o instanceof Number n) return n.doubleValue();
        try { return Double.parseDouble(o.toString()); } catch (Exception e){ return null; }
    }
}
