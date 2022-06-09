package com.works.services;

import com.works.configs.JwtUtil;
import com.works.entities.JWTAdmin;
import com.works.entities.JWTCustomer;
import com.works.entities.JWTLogin;
import com.works.entities.Role;
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
//
//    public Collection rolles(Role rolex)
//    {
//        List<GrantedAuthority> ls = new ArrayList<>();
//        ls.add(new SimpleGrantedAuthority(rolex.getName()));
//        return  ls;
//    }

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
            hm.put(REnum.message, "Bu mail daha kayıt edilmiş");
            hm.put(REnum.result, jwtUser);
            return new ResponseEntity( hm, HttpStatus.NOT_ACCEPTABLE );
        }
    }

    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

//    public ResponseEntity auth(JWTLogin jwtLogin){
//        Map<REnum , Object> hm = new LinkedHashMap<>();
//        try{
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( //1.33-37
//                    jwtLogin.getUsername(),jwtLogin.getPassword()
//            )) ;
//            UserDetails userDetails = loadUserByUsername(jwtLogin.getUsername());
//            String jwt = jwtUtil.generateToken(userDetails);
//            hm.put(REnum.status,true);
//            hm.put(REnum.jwt,jwt);
//            return  new ResponseEntity(hm,HttpStatus.OK);
//        }catch (Exception ex){
//            hm.put(REnum.error,false);
//            hm.put(REnum.error,ex.getMessage());
//            return  new ResponseEntity(hm,HttpStatus.NOT_ACCEPTABLE);
//        }
//    }

//    public JWTUser info() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String userName = auth.getName();
//        Optional<JWTAdmin> optionalJWTAdmin = jwtAdminRepository.findByEmailEqualsIgnoreCase(userName);
//        Optional<JWTCustomer> optionalJWTCustomer = jwtCustomerRepository.findByEmailEqualsIgnoreCase(userName);
//        if ( optionalJWTAdmin.isPresent() ) {
//            return optionalJWTAdmin.get();
//        }
//        return null;
//    }
}