package com.works.restcontrollers;

import com.works.entities.Basket;
import com.works.services.BasketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/basket")
public class BasketRestControllers {
    final BasketService basketService;

    public BasketRestControllers(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody Basket basket){
        return basketService.saveBasket(basket);
    }

    @GetMapping("/list")
    public ResponseEntity list(){
        return basketService.basketList();
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestParam Long id){
        return basketService.deleteBasket(id);
    }

    @PutMapping("/update")
    public ResponseEntity update( @Valid @RequestBody Basket basket){
        return basketService.updateBasket(basket);
    }

}