//package com.works.restcontrollers;
//
//import com.works.entities.Orders;
//import com.works.services.OrderService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/order")
//public class OrderRestControllers {
//    final OrderService orderService;
//
//    public OrderRestControllers(OrderService orderService) {
//        this.orderService = orderService;
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity save(@Valid @RequestBody Orders orders){
//        return orderService.saveOrder(orders);
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity list(){
//        return orderService.orderList();
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity delete(@RequestParam Long orderId){
//        return orderService.deleteOrder(orderId);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity update( @Valid @RequestBody Orders orders){
//        return orderService.updateOrder(orders);
//    }
//
//}