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
//        http.csrf().disable().formLogin().disable();//form bilgisini arar. csrf kodu arama ve bu formu arama diyoruz biz burda
        //ustü yoruma aldık 5.gun
//        rollere gore
        http
                .authorizeRequests() //giriş rolleri ile çalış
                .antMatchers("/register","/auth").permitAll()//permitAll herkese acık olan yetksiiz grilebilrn. burda rol ve giriş şartı aramıyrm
//                .antMatchers("/product/**").hasRole("customer") //hangii servis hangi role calsıır
//                .antMatchers("/note/**").hasRole("admin") //admin sadece muhasebeci gibi hangii. servis hangi role calsıır
//                .antMatchers("/sales/**").hasRole("admin") //hangii servis hangi role calsıır
//                bunllar tanmlardı
                .and() //authorizte ile ilgili için işim bitti diğer ayarları belirtiyorum
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // o ziyerte ozgu bir nesne olusutur sessiondiir bu webte. //burda da bir sesion arar . sesion nesnesi kuruldu mu
                //spring kendi sesionu sadece kabul eder dışardan jwt den de bir nesne oluşturmak ister sessionCreationPolicy ile bu izni alrız
                //arka planda sesion olmadan guvenliği saglayamayız
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); //addFilterBefore oncesinde bunu filtere jwt sini //ses,on kontrou ip kontrollu vb en başta çalışan yer(
        //jwtFilter hangi sındıfı baz larak filtreleyecegini belirtir
        //addFilterBefore oncesinde şunu çalıştır dedigmiz ksımdrı
        //UsernamePasswordAuthenticationFilter.class bu cllas tüürnde calısacak seekilde bir seye sahip ol



    }

    @Bean
    @Override //callspoolde bu yok diye yaptk
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
