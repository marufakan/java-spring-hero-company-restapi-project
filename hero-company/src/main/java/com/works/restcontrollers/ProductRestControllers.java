package com.works.restcontrollers;

import com.works.entities.Product;
import com.works.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductRestControllers {
    final ProductService productService;

    public ProductRestControllers(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/list")
    public ResponseEntity list(){
        return productService.productList();
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam Long pid){
        return productService.deleteProduct(pid);
    }

    @PutMapping("/update")
    public ResponseEntity update( @Valid @RequestBody Product product){
        return productService.updateProduct(product);
    }

}