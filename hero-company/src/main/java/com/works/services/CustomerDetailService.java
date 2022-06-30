package com.works.services;

import com.works.configs.JwtUtil;
import com.works.entities.*;
import com.works.repostories.JWTAdminRepository;
import com.works.repostories.JWTCustomerRepository;
import com.works.utils.REnum;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class CustomerDetailService implements UserDetailsService {

    final JwtUtil jwtUtil;
    final JWTCustomerRepository jwtCustomerRepository;
    final AuthenticationManager authenticationManager;
    public CustomerDetailService(JwtUtil jwtUtil, JWTCustomerRepository jwtCustomerRepository, @Lazy AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.jwtCustomerRepository = jwtCustomerRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public ResponseEntity registerCustomer(JWTCustomer jwtUser){
        Optional<JWTCustomer> optionalJWTUser = jwtCustomerRepository.findByEmailEqualsIgnoreCase(jwtUser.getEmail());
        Map<REnum, Object> hm = new LinkedHashMap();

        if (!optionalJWTUser.isPresent()){
            jwtUser.setPassword( encoder().encode( jwtUser.getPassword()));
            JWTCustomer user = jwtCustomerRepository.save(jwtUser);

            hm.put(REnum.status, true);
            hm.put(REnum.result, user);
            return new ResponseEntity( hm , HttpStatus.OK);
        }else{
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Bu mail daha önce kayıt edilmiş");
            hm.put(REnum.result, jwtUser);
            return new ResponseEntity( hm, HttpStatus.NOT_ACCEPTABLE );
        }
    }

    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    //list
    public ResponseEntity<Map<String ,Object>>  customerList(){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        hm.put(REnum.status,true);
        hm.put(REnum.result, jwtCustomerRepository.findAll());
        return new  ResponseEntity(hm, HttpStatus.OK);
    }

    //delete
    public ResponseEntity<Map<String ,Object>> deleteCustomer(Long cid ){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try {
            jwtCustomerRepository.deleteById(cid);
            hm.put(REnum.status,true);
            return new  ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception ex) {
            hm.put(REnum.status,false);
            hm.put(REnum.message, ex.getMessage());
            return new  ResponseEntity(hm, HttpStatus.BAD_REQUEST);
        }
    }

    //settings customer
    public ResponseEntity<Map<String ,Object>> updateCustomer(SettingsCustomer st){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try{
            Optional<JWTCustomer> oCompany= jwtCustomerRepository.findById(st.getId());
            if(oCompany.isPresent()){
                jwtCustomerRepository.settingsCustomer(st.getFirstName(),st.getLastName(),st.getEmail(),st.getPhone(), st.getId());
                hm.put(REnum.result, st);
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

    public ResponseEntity<Map<String ,Object>> changePassword(ChangePassword st){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try{
            Optional<JWTCustomer> oCompany= jwtCustomerRepository.findById(st.getId());
            if(oCompany.isPresent()){
                String newPassword =  encoder().encode( st.getNewPassword());
                jwtCustomerRepository.changePassword(newPassword,st.getId());
                hm.put(REnum.result, st);
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