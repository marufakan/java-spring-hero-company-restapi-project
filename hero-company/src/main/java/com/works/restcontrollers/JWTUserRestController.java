package com.works.restcontrollers;

import com.works.entities.JWTAdmin;
import com.works.entities.JWTCustomer;
import com.works.entities.JWTLogin;
import com.works.services.AdminDetailService;
import com.works.services.CustomerDetailService;
import com.works.services.JWTUserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("customerRegister")
    public ResponseEntity register(@Valid @RequestBody JWTCustomer jwtUser){
        return customerDetailService.registerCustomer(jwtUser);
    }

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody JWTLogin jwtLogin){
        return jwtUserDetailService.auth(jwtLogin);
    }

}