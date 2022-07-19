//package com.example.libapp.filters;
//
//import com.example.libapp.exceptions.InvalidInputException;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.Objects;
//
//@WebFilter("/register")
//public class AuthRegisterFilter implements Filter {
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String confirmPassword = req.getParameter("confirmPassword");
//        String method = ((HttpServletRequest) req).getMethod();
//        if ("post".equalsIgnoreCase(method)) {
//            if (Objects.isNull(username) || username.equals(""))
//                throw new InvalidInputException("Username can not be null");
//            if (Objects.isNull(password) || password.equals(""))
//                throw new InvalidInputException("Password can not be null");
//            if (!Objects.equals(password, confirmPassword))
//                throw new InvalidInputException("Password did not match");
//        }
//        chain.doFilter(req, res);
//    }
//
//
//}
