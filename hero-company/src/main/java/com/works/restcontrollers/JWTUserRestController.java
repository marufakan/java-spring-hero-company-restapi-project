package com.works.restcontrollers;

import com.works.entities.*;
import com.works.services.AdminDetailService;
import com.works.services.CustomerDetailService;
import com.works.services.JWTUserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class JWTUserRestController {
    final JWTUserDetailService jwtUserDetailService;
    final AdminDetailService adminDetailService;
    final CustomerDetailService customerDetailService;

    public JWTUserRestController(JWTUserDetailService jwtUserDetailService, AdminDetailService adminDetailService, CustomerDetailService customerDetailService) {
        this.jwtUserDetailService = jwtUserDetailService;
        this.adminDetailService = adminDetailService;
        this.customerDetailService = customerDetailService;
    }

    @PostMapping("adminRegister")
    public ResponseEntity register(@Valid @RequestBody JWTAdmin jwtUser){
        return adminDetailService.registerAdmin(jwtUser);
    }

    @PostMapping("/updateAdmin")
    public ResponseEntity updateAdmin(@Valid @RequestBody SettingsAdmin jwtAdmin){
        return adminDetailService.updateAdmin(jwtAdmin);
    }

    @PostMapping("/changePasswordAdmin")
    public ResponseEntity changePassword(@Valid @RequestBody ChangePassword changePassword){
        return adminDetailService.changePassword(changePassword);
    }

    @PostMapping("customerRegister")
    public ResponseEntity register(@Valid @RequestBody JWTCustomer jwtUser){
        return customerDetailService.registerCustomer(jwtUser);
    }

    @GetMapping("/listC")
    public ResponseEntity list(){
        return customerDetailService.customerList();
    }

    @DeleteMapping("/deleteC")
    public ResponseEntity delete(@RequestParam Long id){
        return customerDetailService.deleteCustomer(id);
    }

    @PostMapping("/updateC")
    public ResponseEntity update(@Valid @RequestBody SettingsCustomer customer){
        return customerDetailService.updateCustomer(customer);
    }

    @PostMapping("/changePasswordCustomer")
    public ResponseEntity changePasswordCustomer(@Valid @RequestBody ChangePassword changePassword){
        return customerDetailService.changePassword(changePassword);
    }

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody JWTLogin jwtLogin){
        return jwtUserDetailService.auth(jwtLogin);
    }

}