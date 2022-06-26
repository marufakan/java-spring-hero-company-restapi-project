package com.works.restcontrollers;


import com.works.entities.Category;
import com.works.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryRestControllers {
    final CategoryService categoryService;

    public CategoryRestControllers(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Category category){
        return categoryService.saveCategory(category);
    }

    @GetMapping("/list")
    public ResponseEntity list(){
        return categoryService.categoryList();
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam int cid){
        return categoryService.deleteCategory(cid);
    }

    @PutMapping("/update")
    public ResponseEntity update( @Valid @RequestBody Category category){
        return categoryService.updateCategory(category);
    }

}