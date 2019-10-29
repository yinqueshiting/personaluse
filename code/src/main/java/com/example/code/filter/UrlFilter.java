package com.example.code.filter;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 拦截url打印接口信息
 */
@Slf4j
@WebFilter(urlPatterns = "/*",filterName = "urlFilter")
public class UrlFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String url = request.getRequestURI();
        log.info("url:"+request.getRequestURI());
        //如果是web-socket请求不能转为
        if(StringUtils.isNotBlank(request.getHeader("Upgrade"))){
            log.info("{}请求为websocket",url);
            filterChain.doFilter(servletRequest, servletResponse);

        }else {
        /*if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))){
            log.info("ajax请求");
        }else{
            log.info("不是ajax请求");
        }*/
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}


