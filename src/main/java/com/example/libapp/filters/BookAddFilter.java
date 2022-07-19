package com.example.libapp.filters;

import com.example.libapp.exceptions.InvalidInputException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Objects;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 8:52 AM 7/14/22 on Thursday in July
 */
//@WebFilter("/booksAdd")
public class BookAddFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
//        String name = servletRequest.getParameter("name");
//        String author = servletRequest.getParameter("author");
//        String description = servletRequest.getParameter("description");
//        String pageCount = servletRequest.getParameter("pageCount");
//        Part file = ((HttpServletRequest) servletRequest).getPart("file");
//        Part cover = ((HttpServletRequest) servletRequest).getPart("cover");
//        String method = ((HttpServletRequest) servletRequest).getMethod();
//        if ("POST".equalsIgnoreCase(method)) {
//            if (Objects.isNull(name) || name.equals(""))
//                throw new InvalidInputException("Name can not be null");
//            if (Objects.isNull(author) || author.equals(""))
//                throw new InvalidInputException("Author can not be null");
//            if (Objects.isNull(description) || description.equals(""))
//                throw new InvalidInputException("Description can not be null");
//            if (Objects.isNull(pageCount) || pageCount.equals(""))
//                throw new InvalidInputException("PageCount can not be null");
//            if (Objects.isNull(file)) {
//                throw new InvalidInputException("File cannot be null");
//            }
//            if (Objects.isNull(cover)) {
//                throw new InvalidInputException("Cover cannot be null");
//            }
//        }
//        filterChain.doFilter(servletRequest, servletResponse);
    }
}
