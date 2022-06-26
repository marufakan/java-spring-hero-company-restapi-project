package com.works.configs;

import com.works.services.JWTUserDetailService;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class FilterConfig implements Filter {//servlet olanı imp et
    final JWTUserDetailService jwtUserDetailService;
    public FilterConfig(JWTUserDetailService jwtUserDetailService) {
        this.jwtUserDetailService = jwtUserDetailService;
    }

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String ip = req.getLocalAddr();
        String url = req.getRequestURI();
        String info = req.getHeader("User-Agent");

        filterChain.doFilter(req,res);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

}
