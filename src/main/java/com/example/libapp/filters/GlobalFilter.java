package com.example.libapp.filters;

import com.example.libapp.exceptions.InvalidInputException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Objects;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 9:53 AM 7/18/22 on Monday in July
 */
@WebFilter("/*")
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String method = ((HttpServletRequest) servletRequest).getMethod();
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();

        if (requestURI.equals("/login") && method.equalsIgnoreCase("GET") && ((HttpServletRequest) servletRequest).getSession().getAttribute("auth_session") == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (requestURI.equals("/register") && method.equalsIgnoreCase("GET")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if ("POST".equalsIgnoreCase(method) && requestURI.equals("/register")) {
            String username = servletRequest.getParameter("username");
            String password = servletRequest.getParameter("password");
            String confirmPassword = servletRequest.getParameter("confirmPassword");
            if (Objects.isNull(username) || username.equals(""))
                throw new InvalidInputException("Username can not be null");
            if (Objects.isNull(password) || password.equals(""))
                throw new InvalidInputException("Password can not be null");
            if (!Objects.equals(password, confirmPassword))
                throw new InvalidInputException("Password did not match");
        }

        if ("POST".equalsIgnoreCase(method) && requestURI.equals("/login")) {
            String username = servletRequest.getParameter("username");
            String password = servletRequest.getParameter("password");
            if (Objects.isNull(username) || username.equals(""))
                throw new InvalidInputException("Username can not be null");
            if (Objects.isNull(password) || password.equals(""))
                throw new InvalidInputException("Password can not be null");
        }

        if (requestURI.equals("/booksAdd") && method.equalsIgnoreCase("POST")) {
            String name = servletRequest.getParameter("name");
            String author = servletRequest.getParameter("author");
            String description = servletRequest.getParameter("description");
            String pageCount = servletRequest.getParameter("pageCount");
            Part file = ((HttpServletRequest) servletRequest).getPart("file");
            Part cover = ((HttpServletRequest) servletRequest).getPart("cover");
            if (Objects.isNull(name) || name.equals(""))
                throw new InvalidInputException("Name can not be null");
            if (Objects.isNull(author) || author.equals(""))
                throw new InvalidInputException("Author can not be null");
            if (Objects.isNull(description) || description.equals(""))
                throw new InvalidInputException("Description can not be null");
            if (Objects.isNull(pageCount) || pageCount.equals(""))
                throw new InvalidInputException("PageCount can not be null");
            if (Objects.isNull(file)) {
                throw new InvalidInputException("File cannot be null");
            }
            if (Objects.isNull(cover)) {
                throw new InvalidInputException("Cover cannot be null");
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
