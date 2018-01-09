package com.infoshareacademy.searchengine.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;

@WebFilter(
        filterName = "AuthenticationFilter",
        urlPatterns = {"/*"})  // -> web.xml <security-constraint>
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Principal user = req.getUserPrincipal();
        HttpSession session = req.getSession();

        session.setAttribute("isLogged", (user != null));
        session.setAttribute("loggedUser", ((user == null) ? null : user.getName()));

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
