package com.works.services;

import com.works.entities.Product;
import com.works.entities.Sales;
import com.works.repostories.SalesRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SalesService {

    final SalesRepository salesRepository;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public ResponseEntity<Map<String ,Object>> saveSale(Sales sales){
        HttpHeaders headers = new HttpHeaders();
        headers.add("custom","1234556");
        System.out.println(sales);

        Sales p=salesRepository.save(sales);
        Map<REnum,Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status,true);
        hm.put(REnum.result, p);
        return new  ResponseEntity(hm,headers, HttpStatus.OK);
    }

    //list
    public ResponseEntity<Map<String ,Object>>  salesList(){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status,true);
        hm.put(REnum.result, salesRepository.findAll());
        return new  ResponseEntity(hm, HttpStatus.OK);
    }
}
