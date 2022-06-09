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
public class AdminDetailService implements UserDetailsService {

    final JwtUtil jwtUtil;
    final JWTAdminRepository jwtAdminRepository;
    final AuthenticationManager authenticationManager;
    public AdminDetailService(JwtUtil jwtUtil, JWTAdminRepository jwtAdminRepository, JWTCustomerRepository jwtCustomerRepository, @Lazy AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.jwtAdminRepository = jwtAdminRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public ResponseEntity registerAdmin(JWTAdmin jwtUser){
        Optional<JWTAdmin> optionalJWTUser = jwtAdminRepository.findByEmailEqualsIgnoreCase(jwtUser.getEmail());
        Map<REnum, Object> hm = new LinkedHashMap();

        if (!optionalJWTUser.isPresent()){
            jwtUser.setPassword( encoder().encode( jwtUser.getPassword()));
            JWTAdmin user = jwtAdminRepository.save(jwtUser);

            hm.put(REnum.status, true);
            hm.put(REnum.result, user);
            return new ResponseEntity( hm , HttpStatus.OK);
        }else{
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Bu mail daha kayıt edilmiş");
            hm.put(REnum.result, jwtUser);
            return new ResponseEntity( hm, HttpStatus.NOT_ACCEPTABLE );
        }
    }

    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    //update
    public ResponseEntity<Map<String ,Object>> updateAdmin(SettingsAdmin st){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try{
            Optional<JWTAdmin> oCompany= jwtAdminRepository.findById(st.getId());
            if(oCompany.isPresent()){
                jwtAdminRepository.settingsAdmin(st.getCompanyName(),st.getFirstName(),st.getLastName(),st.getEmail(), st.getId());
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

    //update
    public ResponseEntity<Map<String ,Object>> changePassword(ChangePassword st){
        Map<REnum,Object> hm = new LinkedHashMap<>();
        try{
            Optional<JWTAdmin> oCompany= jwtAdminRepository.findById(st.getId());
            if(oCompany.isPresent()){
                String newPassword =  encoder().encode( st.getNewPassword());
                jwtAdminRepository.changePassword(newPassword,st.getId());
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