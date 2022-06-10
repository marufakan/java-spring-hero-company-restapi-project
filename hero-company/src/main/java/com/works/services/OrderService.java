//package com.works.services;
//
//import com.works.entities.Orders;
//import com.works.repostories.OrdersRepository;
//import com.works.utils.REnum;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//import java.util.Optional;
//
//@Service
//public class OrderService {
//    final OrdersRepository ordersRepository;
//
//    public OrderService(OrdersRepository ordersRepository) {
//        this.ordersRepository = ordersRepository;
//    }
//
//    //post
//    public ResponseEntity<Map<String ,Object>> saveOrder(Orders orders){
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("custom","1234556");
//        Orders p=ordersRepository.save(orders);
//
//        Map<REnum,Object> hm = new LinkedHashMap<>();
//        hm.put(REnum.status,true);
//        hm.put(REnum.result, p);
//        return new  ResponseEntity(hm,headers, HttpStatus.OK);
//    }
//
//    //list
//    public ResponseEntity<Map<String ,Object>>  orderList(){
//        Map<REnum,Object> hm = new LinkedHashMap<>();
//        hm.put(REnum.status,true);
//        hm.put(REnum.result, ordersRepository.findAll());
//        return new  ResponseEntity(hm, HttpStatus.OK);
//    }
//
//    //delete
//    public ResponseEntity<Map<String ,Object>> deleteOrder(Long orderId ){
//        Map<REnum,Object> hm = new LinkedHashMap<>();
//        try {
//            ordersRepository.deleteById(orderId);
//            hm.put(REnum.status,true);
//            return new  ResponseEntity(hm, HttpStatus.OK);
//        }catch (Exception ex) {
//            hm.put(REnum.status,false);
//            hm.put(REnum.message, ex.getMessage());
//            return new  ResponseEntity(hm, HttpStatus.BAD_REQUEST);
//        }
//
//    }
//
//    //update
//    public ResponseEntity<Map<String ,Object>> updateOrder(Orders orders){
//        Map<REnum,Object> hm = new LinkedHashMap<>();
//        try{
//            Optional<Orders> oProduct = ordersRepository.findById(orders.getOrderId());
//            if(oProduct.isPresent()){
//                ordersRepository.saveAndFlush(orders);
//                hm.put(REnum.result, orders);
//                hm.put(REnum.status, true);
//                return new  ResponseEntity(hm, HttpStatus.OK);
//            }else{
//                hm.put(REnum.status, false);
//                return new  ResponseEntity(hm, HttpStatus.BAD_REQUEST);
//            }
//        }catch (Exception e){
//            hm.put(REnum.status, false);
//            hm.put(REnum.message, e.getMessage());
//        }
//        return new  ResponseEntity(hm, HttpStatus.BAD_REQUEST);
//    }
//}
