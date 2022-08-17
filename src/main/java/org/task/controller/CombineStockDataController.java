package org.task.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.task.model.Stock;
import org.task.service.CombineStockDataService;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class CombineStockDataController {

    private final CombineStockDataService service;

    public CombineStockDataController(CombineStockDataService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Stock>> get() {
        return ResponseEntity.ok(service.getStocks());
    }
}
