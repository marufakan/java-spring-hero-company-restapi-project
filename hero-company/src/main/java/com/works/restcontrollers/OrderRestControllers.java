package com.works.restcontrollers;

import com.works.entities.Basket;
import com.works.entities.Orders;
import com.works.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderRestControllers {
    final OrderService orderService;

    public OrderRestControllers(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/save")
    public ResponseEntity save(){
        return orderService.saveOrder();
    }

    @GetMapping("/list")
    public ResponseEntity list(){
        return orderService.orderList();
    }

    @GetMapping("/listReport")
    public ResponseEntity listReport(){
        return orderService.orderReportList();
    }

    @GetMapping("/listReportDetails")
    public ResponseEntity listReportDetails(){
        return orderService.orderReportDetails();
    }

}