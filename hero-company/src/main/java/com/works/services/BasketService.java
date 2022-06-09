package com.works.services;

import com.works.entities.Basket;
import com.works.repostories.BasketRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BasketService {
    final BasketRepository basketRepository;

    public BasketService(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    //post
    public ResponseEntity<Map<String ,Object>> saveBasket(Basket basket){
        HttpHeaders headers = new HttpHeaders();
        headers.add("custom","1234556");
        Basket p=basketRepository.save(basket);

        Map<REnum,Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status,true);
        hm.put(REnum.result, p);
        return new  ResponseEntity(hm,headers, HttpStatus.OK);
    }

    //list
    public ResponseEntity<Map<String ,Object>>  basketList(){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status,true);
        hm.put(REnum.result, basketRepository.findAll());
        return new  ResponseEntity(hm, HttpStatus.OK);
    }

    //delete
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
            Optional<Basket> oBasket = basketRepository.findById(basket.getBid());
            if(oBasket.isPresent()){
                basketRepository.saveAndFlush(basket);
                hm.put(REnum.result, basket);
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
