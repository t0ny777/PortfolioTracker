/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.nurda.spring.portfoliotracker1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class PortfolioTracker1 {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioTracker1.class, args);
    
        
    }
    @GetMapping("/simple-test")
    public String test() {
        return "This MUST work!";
    }
}

