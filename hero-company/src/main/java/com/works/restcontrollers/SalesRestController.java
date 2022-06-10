package com.works.restcontrollers;

import com.works.entities.Sales;
import com.works.services.SalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sales")
public class SalesRestController {
    final SalesService salesService;

    public SalesRestController(SalesService salesService) {
        this.salesService = salesService;
    }
    @PostMapping("save")
    public ResponseEntity save(@RequestBody Sales sales){
        return salesService.saveSale(sales);
    }

    @GetMapping("/list")
    public ResponseEntity list(){
        return salesService.salesList();
    }

}
