package com.infoshareacademy.searchengine.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(
        filterName = "SalaryIncrementFilter",
        urlPatterns = {"/welcome-user"},
        initParams = {
                @WebInitParam(name = "initialSalary", value = "1280")
        })
public class SalaryIncrementFilter implements Filter {

    double initialSalary;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        initialSalary = Double.parseDouble(filterConfig.getInitParameter("initialSalary"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String reqSalary = servletRequest.getParameter("salary");
        if (reqSalary == null || reqSalary.isEmpty()){
            reqSalary = "0.0";
        }

        double tmpSalary = Double.parseDouble(reqSalary);
        if (tmpSalary < 100) {
            tmpSalary = initialSalary;
        }

        servletRequest.setAttribute("salary", tmpSalary);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        initialSalary = 0.0;
    }
}
