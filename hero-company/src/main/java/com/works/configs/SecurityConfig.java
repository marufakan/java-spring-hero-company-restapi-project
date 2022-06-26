package com.works.configs;

import com.works.services.JWTUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration //WebSecurityConfigurerAdapter yenisi cıkmıs ama kullanabilirsin
public class SecurityConfig extends WebSecurityConfigurerAdapter {//yetkilendirmler burdna yonetilecke

    final JwtFilter jwtFilter;
    final JWTUserDetailService jwtUserDetailService;
    public SecurityConfig(JwtFilter jwtFilter, JWTUserDetailService jwtUserDetailService) {
        this.jwtFilter = jwtFilter;
        this.jwtUserDetailService = jwtUserDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailService).passwordEncoder(jwtUserDetailService.encoder());
    }

    // role ve yönetim
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/customerRegister","/auth").permitAll()
                .antMatchers("/basket/**").hasRole("customer")
                .antMatchers("/adminRegister","/updateAdmin","/changePasswordAdmin").hasRole("admin")
                .antMatchers("/changePasswordCustomer","/listC","/deleteC","/updateC").hasRole("customer")
                .antMatchers("/listC","/deleteC","/updateC").hasRole("admin")
                .antMatchers("/listC","/deleteC","/updateC").hasRole("admin")
                .antMatchers("/category/list").hasRole("customer")
                .antMatchers("/category/**").hasRole("admin")
                .antMatchers("/product/**").hasRole("admin")
                .antMatchers("/product/filterCategory","/product/search").hasRole("customer")
                .antMatchers("/order/save").hasRole("customer")
                .antMatchers("/order/list","/order/listReport","/order/listReportDetails").hasRole("admin")
                .and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
