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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class JWTUserDetailService implements UserDetailsService {

    final JwtUtil jwtUtil;
    final JWTAdminRepository jwtAdminRepository;
    final JWTCustomerRepository jwtCustomerRepository;
    final AuthenticationManager authenticationManager;
    public JWTUserDetailService(JwtUtil jwtUtil, JWTAdminRepository jwtAdminRepository, JWTCustomerRepository jwtCustomerRepository, @Lazy AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.jwtAdminRepository = jwtAdminRepository;
        this.jwtCustomerRepository = jwtCustomerRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JWTAdmin> optionalJWTAdmin = jwtAdminRepository.findByEmailEqualsIgnoreCase(username);
        Optional<JWTCustomer> optionalJWTCustomer = jwtCustomerRepository.findByEmailEqualsIgnoreCase(username);
        if(optionalJWTAdmin.isPresent() & !optionalJWTCustomer.isPresent()){
            JWTAdmin u = optionalJWTAdmin.get();
            UserDetails userDetails = new User(
                    u.getEmail(),
                    u.getPassword(),
                    u.isEnabled(),
                    u.isTokenExpired(),
                    true,
                    true,
                    rolles(u.getRoles())
            );
            return userDetails;
        }
        else if((optionalJWTCustomer.isPresent()) & (!optionalJWTAdmin.isPresent())){
            JWTCustomer u = optionalJWTCustomer.get();
            UserDetails userDetails = new User(
                    u.getEmail(),
                    u.getPassword(),
                    u.isEnabled(),
                    u.isTokenExpired(),
                    true,
                    true,
                    rolles(u.getRoles())
            );
            return userDetails;
        }
        else {
            throw new UsernameNotFoundException("Böyle bir kullanıcı yok");
        }
    }

    public Collection rolles(Role rolex)
    {
        List<GrantedAuthority> ls = new ArrayList<>();
        ls.add(new SimpleGrantedAuthority(rolex.getName()));
        return  ls;
    }

    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    public ResponseEntity auth(JWTLogin jwtLogin){
        Map<REnum , Object> hm = new LinkedHashMap<>();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtLogin.getUsername(),jwtLogin.getPassword()
            )) ;
            UserDetails userDetails = loadUserByUsername(jwtLogin.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            hm.put(REnum.status,true);
            hm.put(REnum.jwt,jwt);

            return  new ResponseEntity(hm,HttpStatus.OK);
        }catch (Exception ex){
            hm.put(REnum.status,false);
            hm.put(REnum.error,ex.getMessage());

            return  new ResponseEntity(hm,HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public String infoCustomer() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Optional<JWTCustomer> optionalJWTUser = jwtCustomerRepository.findByEmailEqualsIgnoreCase(userName);
        if ( optionalJWTUser.isPresent() ) {
            return optionalJWTUser.get().getEmail();
        }
        return null;
    }

    public String infoAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        Optional<JWTCustomer> optionalJWTUser = jwtCustomerRepository.findByEmailEqualsIgnoreCase(userName);
        if ( optionalJWTUser.isPresent() ) {
            return optionalJWTUser.get().getEmail();
        }
        return null;
    }
}