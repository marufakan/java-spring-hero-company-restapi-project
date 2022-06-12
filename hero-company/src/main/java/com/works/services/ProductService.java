package com.works.services;

import com.works.entities.Product;
import com.works.repostories.CategoryRepository;
import com.works.repostories.ProductRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {
    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    //list
    public ResponseEntity<Map<String ,Object>>  searchProduct(String value){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status,true);
        hm.put(REnum.result, productRepository.findByNameLikeIgnoreCaseAndDetailLikeIgnoreCase(value,value));
        return new  ResponseEntity(hm, HttpStatus.OK);
    }

    //post
    public ResponseEntity<Map<String ,Object>> filterCategory(int id){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status,true);
        hm.put(REnum.result, productRepository.findByCategoryIdEquals(id));
        return new  ResponseEntity(hm, HttpStatus.OK);
    }

    //post
    public ResponseEntity<Map<String ,Object>> save(Product product){
        HttpHeaders headers = new HttpHeaders();
        headers.add("custom","1234556");
        Map<REnum,Object> hm = new LinkedHashMap<>();
        if(categoryRepository.findById(product.getCategoryId()).isPresent()){
            Product p=productRepository.save(product);
            hm.put(REnum.status,true);
            hm.put(REnum.result, p);
            return new  ResponseEntity(hm,headers, HttpStatus.OK);
        }
        else {
            hm.put(REnum.status,false);
            hm.put(REnum.error, "category is not found");
            return new  ResponseEntity(hm,headers, HttpStatus.NOT_FOUND);
        }
    }

    //list
    public ResponseEntity<Map<String ,Object>>  productList(){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status,true);
        hm.put(REnum.result, productRepository.findAll());
        return new  ResponseEntity(hm, HttpStatus.OK);
    }

    //delete
    public ResponseEntity<Map<String ,Object>> deleteProduct(Long pid ){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try {
            productRepository.deleteById(pid);
            hm.put(REnum.status,true);
            return new  ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception ex) {
            hm.put(REnum.status,false);
            hm.put(REnum.message, ex.getMessage());
            return new  ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }

    }

    //update Product
    public ResponseEntity<Map<String ,Object>> updateProduct(Product product){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try{
            Optional<Product> oProduct = productRepository.findById(product.getPid());
            if(oProduct.isPresent()){
                productRepository.saveAndFlush(product);
                hm.put(REnum.result, product);
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
