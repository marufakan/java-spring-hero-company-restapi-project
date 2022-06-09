package com.works.restcontrollers;//package com.works.restcontrollers;
//
//
//import com.works.entities.Customer;
//import com.works.services.CustomerService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RestController
//@RequestMapping("/customer")
//public class CustomerRestControllers {
//    final CustomerService customerService;
//
//    public CustomerRestControllers(CustomerService customerService) {
//        this.customerService = customerService;
//    }
//
//    @PostMapping("/save")
//    public ResponseEntity save(@Valid @RequestBody Customer customer){
//        return customerService.customerSave(customer);
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity list(){
//        return customerService.customerList();
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity delete(@RequestParam Long cid){
//        return customerService.deleteCustomer(cid);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity update( @Valid @RequestBody Customer customer){
//        return customerService.updateCustomer(customer);
//    }
//
//}