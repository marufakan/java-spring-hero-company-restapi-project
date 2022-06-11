package com.works.services;


import com.works.entities.Category;
import com.works.repostories.CategoryRepository;
import com.works.utils.REnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoryService {
    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    //save category
    public ResponseEntity<Map<String ,Object>> saveCategory(Category category){
        HttpHeaders headers = new HttpHeaders();
        headers.add("custom","1234556");
        Category p=categoryRepository.save(category);

        Map<REnum,Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status,true);
        hm.put(REnum.result, p);
        return new  ResponseEntity(hm,headers, HttpStatus.OK);
    }

    //list
    public ResponseEntity<Map<String ,Object>>  categoryList(){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status,true);
        hm.put(REnum.result, categoryRepository.findAll());
        return new  ResponseEntity(hm, HttpStatus.OK);
    }

    //delete
    public ResponseEntity<Map<String ,Object>> deleteCategory(int pid ){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try {
            categoryRepository.deleteById(pid);
            hm.put(REnum.status,true);
            return new  ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception ex) {
            hm.put(REnum.status,false);
            hm.put(REnum.message, ex.getMessage());
            return new  ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }

    }

    //update
    public ResponseEntity<Map<String ,Object>> updateCategory(Category category){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try{
            Optional<Category> oCategory = categoryRepository.findById(category.getCategoryId());
            if(oCategory.isPresent()){
                categoryRepository.saveAndFlush(category);
                hm.put(REnum.result, category);
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
