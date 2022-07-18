package com.example.libapp.servlets.book;

import com.example.libapp.configs.ApplicationContextHolder;
import com.example.libapp.domain.Book;
import com.example.libapp.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 11:05 AM 7/16/22 on Saturday in July
 */
@WebServlet("/status")
public class AdminBookServlet extends HttpServlet {
    private final BookService bookService = ApplicationContextHolder.getBean(BookService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status = req.getParameter("status");
        String name = req.getParameter("name");
        if (status.equalsIgnoreCase(Book.Status.CANCELED.name())) {
            bookService.deleteBook(name);
            resp.sendRedirect("/");
        }
        if (status.equalsIgnoreCase(Book.Status.PINNED.name())) {
            bookService.pinBook(name);
            resp.sendRedirect("/");
        }
    }
}
