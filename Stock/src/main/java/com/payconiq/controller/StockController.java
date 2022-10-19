package com.payconiq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.payconiq.entity.Stock;
import com.payconiq.pojo.RequestPayload;
import com.payconiq.service.StockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class StockController {
    private static Logger logger= LoggerFactory.getLogger(StockController.class);

	@Autowired 
	StockService stockService;
	
    
    @PostMapping(value = "/api/stocks")
    public ResponseEntity<?>  stocksPost(@RequestBody RequestPayload requestPayload) {
        Map<Object, Object> returnValue = new HashMap<Object, Object>();
        stockService.saveStock(requestPayload);
        returnValue.put("status", "success");
    	return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @GetMapping(value = "/api/stocks/{id}")
    public ResponseEntity<?>  stocksGet(@PathVariable Long id) {
        Map<Object, Object> returnValue = new HashMap<Object, Object>();
        Optional stock = stockService.getStock(id);
        if (stock.isPresent()) {
            returnValue.put("status", "success");
        	returnValue.put("stock", stock.get());
        }
        else {
        	returnValue.put("stock", null);
        	returnValue.put("status", "No stock found.");
        }
    	return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }
    
    @GetMapping(value = "/api/stocks")
    public ResponseEntity<?>  stocksAllGet(@RequestParam(required=false) Map<String,String> qparams) {
        Map<Object, Object> returnValue = new HashMap<Object, Object>();
        String pageNumber = qparams.get("pageNumber");
        String pageSize = qparams.get("pageSize");
        List<Stock> stockList = stockService.getStockAll(pageNumber, pageSize);
        returnValue.put("stocks", stockList);
        returnValue.put("status", "success");
    	return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }
    
    @PutMapping(value = "/api/stocks")
    public ResponseEntity<?>  stocksPut() {
        Map<Object, Object> returnValue = new HashMap<Object, Object>();
    	return new ResponseEntity<>(returnValue, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PatchMapping(value = "/api/stocks/{id}")
    public ResponseEntity<?>  stocksPatch(@PathVariable Long id, @RequestBody double currentPrice) {
        Map<Object, Object> returnValue = new HashMap<Object, Object>();
        stockService.updateStockPrice(id, currentPrice);
        returnValue.put("status", "success");
    	return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }


    @DeleteMapping(value = "/api/stocks/{id}")
    public ResponseEntity<?>  stocksDelete(@PathVariable Long id) {
        Map<Object, Object> returnValue = new HashMap<Object, Object>();
        stockService.deleteStock(id);
        returnValue.put("status", "success");
    	return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

}
