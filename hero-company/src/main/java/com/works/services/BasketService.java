package com.works.services;

import com.works.entities.Basket;
import com.works.entities.Product;
import com.works.repostories.BasketRepository;
import com.works.repostories.JWTCustomerRepository;
import com.works.repostories.ProductRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasketService {
    final BasketRepository basketRepository;
    final JWTUserDetailService jwtUserDetailService;
    final ProductRepository productRepository;
    final ProductService productService;

    public BasketService(BasketRepository basketRepository, JWTCustomerRepository jwtCustomerRepository, JWTUserDetailService jwtUserDetailService, ProductRepository productRepository, ProductService productService) {
        this.basketRepository = basketRepository;
        this.jwtUserDetailService = jwtUserDetailService;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    //post
    public ResponseEntity<Map<String ,Object>> saveBasket(Basket basket){
        HttpHeaders headers = new HttpHeaders();
        headers.add("custom","1234556");
        //ürün var mı ve stock yeterli mi kontrol ediliyor
        Optional<Product> oProduct = productRepository.findByPidAndStockGreaterThanEqual(basket.getPid(),basket.getCount());
        Map<REnum,Object> hm = new LinkedHashMap<>();
        if(oProduct.isPresent()){
            Product product=productRepository.findByPid(basket.getPid());
            product.setStock(product.getStock()-basket.getCount());
            productService.updateProduct(product);
            Basket basket1=basket;
            System.out.println("---------------------------------------------------------------------");
            try{
                List<Basket> ls=basketRepository.findByCreatedByEqualsIgnoreCaseAndStatusEquals(jwtUserDetailService.infoCustomer(), 1);
                if(ls.size()>0){
                    basket1.setUuid(ls.get(0).getUuid());
                }
                else {
                    basket1.setUuid(UUID.randomUUID().toString());
                }
            }catch (Exception e){

            }
            Basket p=basketRepository.save(basket);
            hm.put(REnum.status,true);
            hm.put(REnum.result, p);
        }
        else{
            hm.put(REnum.status,false);
            hm.put(REnum.error, "stock is insufficient");
        }
        return new  ResponseEntity(hm,headers, HttpStatus.OK);
    }

    //list
    //müşteri kendi sepetindeki güncel ürünleri görmek için
    public ResponseEntity<Map<String ,Object>>  basketList(){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        String createdBy = jwtUserDetailService.infoCustomer();
        System.out.println(createdBy);

        hm.put(REnum.status,true);
        hm.put(REnum.result, basketRepository.findByCreatedByEqualsIgnoreCaseAndStatusEquals(createdBy,1));
        return new  ResponseEntity(hm, HttpStatus.OK);
    }

    //delete
    //müşteri sepeteki bir ürünü iptal ettmek ve silmek isterse kullanılacak
    public ResponseEntity<Map<String ,Object>> deleteBasket(Long id ){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try {
            basketRepository.deleteById(id);
            hm.put(REnum.status,true);
            return new  ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception ex) {
            hm.put(REnum.status,false);
            hm.put(REnum.message, ex.getMessage());
            return new  ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }

    }

    //update
    public ResponseEntity<Map<String ,Object>> updateBasket(Basket basket){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try{
            Optional<Basket> oBasket = basketRepository.findByIdIs(basket.getId());
            if(oBasket.isPresent()){
                Basket basket1=basket;
                basket1.setStatus(0);
                basketRepository.saveAndFlush(basket1);
                hm.put(REnum.result, basket1);
                hm.put(REnum.status, true);
                return new  ResponseEntity(hm, HttpStatus.OK);
            }else{
                hm.put(REnum.status, false);
                return new  ResponseEntity(hm, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            hm.put(REnum.status, false);
            hm.put(REnum.message, e.getMessage());
        }
        return new  ResponseEntity(hm, HttpStatus.BAD_REQUEST);
    }
}
