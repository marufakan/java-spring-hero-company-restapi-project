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

    //db varlık deneteimi aautj
    //http security rolller gohre tanjm

    final JwtFilter jwtFilter;
    final JWTUserDetailService jwtUserDetailService;
    public SecurityConfig(JwtFilter jwtFilter, JWTUserDetailService jwtUserDetailService) {
        this.jwtFilter = jwtFilter;
        this.jwtUserDetailService = jwtUserDetailService;
    }

    // veritabanında yönetim, kullanıcı varlık denetimi
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super miras olan defulatu alır
        auth.userDetailsService(jwtUserDetailService).passwordEncoder(jwtUserDetailService.encoder());//bırası springe ilk atarkmdır //userDetailservice tipinde bir şeı bekeler
        //springin şifr alg biz conf eedbilrz şayet yapacaksak bunu bildirmemzi gerekir
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
                .and() //authorizte ile ilgili için işim bitti diğer ayarları belirtiyorum
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); //addFilterBefore oncesinde bunu filtere jwt sini //ses,on kontrou ip kontrollu vb en başta çalışan yer(

    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
