/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nurda.spring.portfoliotracker1.repo;
import com.nurda.spring.portfoliotracker1.model.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 *
 * @author Asus
 */
public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
     List<PortfolioItem> findByAssetId(String assetId);
    void deleteByAssetId(String assetId);
}




