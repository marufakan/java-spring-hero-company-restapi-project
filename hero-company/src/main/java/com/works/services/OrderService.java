package com.works.services;

import com.works.entities.Basket;
import com.works.entities.OrderReport;
import com.works.entities.OrderReportDetails;
import com.works.entities.Orders;
import com.works.repostories.*;
import com.works.utils.REnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    final OrdersRepository ordersRepository;
    final BasketRepository basketRepository;
    final JWTUserDetailService jwtUserDetailService;
    final BasketService basketService;
    final ProductRepository productRepository;
    final OrderReportRepository orderReportRepository;
    final OrderReportDetailsRepository orderReportDetailsRepository;

    public OrderService(OrdersRepository ordersRepository, BasketRepository basketRepository, JWTUserDetailService jwtUserDetailService, BasketService basketService, ProductRepository productRepository, OrderReportRepository orderReportRepository, OrderReportDetailsRepository orderReportDetailsRepository) {
        this.ordersRepository = ordersRepository;
        this.basketRepository = basketRepository;
        this.jwtUserDetailService = jwtUserDetailService;
        this.basketService = basketService;
        this.productRepository = productRepository;
        this.orderReportRepository = orderReportRepository;
        this.orderReportDetailsRepository = orderReportDetailsRepository;
    }

    //post
    public ResponseEntity<Map<String ,Object>> saveOrder(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("custom","1234556");
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try{
            List<Basket> basketList=basketRepository.findByCreatedByEqualsIgnoreCaseAndStatusEquals(jwtUserDetailService.infoCustomer(), 1);
            Orders newOrder=fncorders(basketList);
            Orders orders=ordersRepository.save(newOrder);

            hm.put(REnum.status,true);
            hm.put(REnum.result, orders);

            return new  ResponseEntity(hm,headers, HttpStatus.OK);
        }
        catch (Exception e){
            return new  ResponseEntity(hm,headers, HttpStatus.BAD_REQUEST);
        }
    }

    //list
    public ResponseEntity<Map<String ,Object>>  orderList(){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status,true);
        hm.put(REnum.result, ordersRepository.findAll());
        return new  ResponseEntity(hm, HttpStatus.OK);
    }

    public Orders fncorders(List<Basket> basketList){
        Orders orders=new Orders();
        orders.setUuid(basketList.get(0).getUuid());
        int total=0;
        for (Basket item:basketList) {
            int price = (productRepository.findByPid(item.getPid())).getPrice();
            System.out.println(price);
            total += ((item.getCount())*price);
            basketService.updateBasket(item);//satılan ürünleri basketten güncelleme
            orders.setTotal(total);
        }

        return orders;
    }

    public ResponseEntity<Map<String ,Object>>  orderReportList(){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        List<OrderReport> orderReport= orderReportRepository.ordersCustomerReport();
        hm.put(REnum.status,true);
        hm.put(REnum.result, orderReport);
        return new  ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity<Map<String ,Object>>  orderReportDetails(){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        List<OrderReportDetails> orderReport= orderReportDetailsRepository.oRDetails();
        hm.put(REnum.status,true);
        hm.put(REnum.result, orderReport);
        return new  ResponseEntity(hm, HttpStatus.OK);
    }

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

//    //update
//    public ResponseEntity<Map<String ,Object>> updateOrder(Orders orders){
//        Map<REnum,Object> hm = new LinkedHashMap<>();
//        try{
//            Optional<Orders> oOrders = ordersRepository.findById(orders.getId());
//            if(oOrders.isPresent()){
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
}
