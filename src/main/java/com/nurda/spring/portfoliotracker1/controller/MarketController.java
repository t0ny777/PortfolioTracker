/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nurda.spring.portfoliotracker1.controller;




import com.nurda.spring.portfoliotracker1.dto.MarketAssetDto;
import com.nurda.spring.portfoliotracker1.service.MarketService;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    private final MarketService market;

    public MarketController(MarketService market) {
        this.market = market;
    }

    @GetMapping("/top")
    public List<MarketAssetDto> top(@RequestParam(defaultValue = "100") int limit) {
        return market.top(limit);
    }

    @GetMapping("/price/{id}")
    public Map<String, Object> price(@PathVariable String id) {
        return Map.of("id", id, "price", market.priceOf(id));
    }
}
