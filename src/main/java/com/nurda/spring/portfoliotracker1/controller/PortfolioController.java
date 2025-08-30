package com.nurda.spring.portfoliotracker1.controller;



import com.nurda.spring.portfoliotracker1.dto.AddPortfolioItemRequest;
import com.nurda.spring.portfoliotracker1.dto.AggregatedAssetDto;
import com.nurda.spring.portfoliotracker1.model.PortfolioItem;
import com.nurda.spring.portfoliotracker1.service.PortfolioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final PortfolioService service;

    public PortfolioController(PortfolioService service) {
        this.service = service;
    }

    @GetMapping
    public List<AggregatedAssetDto> getPortfolioAggregated() {
        return service.aggregated();
    }

    @PostMapping
    public PortfolioItem addItem(@Valid @RequestBody AddPortfolioItemRequest request) {
        return service.add(request);
    }

    @DeleteMapping("/{assetId}")
    public void remove(@PathVariable String assetId) {
        service.removeAsset(assetId);
    }
}
