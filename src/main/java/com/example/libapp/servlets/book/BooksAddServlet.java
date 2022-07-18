package com.example.libapp.servlets.book;

import com.example.libapp.configs.ApplicationContextHolder;
import com.example.libapp.dto.BookCreateDTO;
import com.example.libapp.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 11:29 PM 7/13/22 on Wednesday in July
 */
@WebServlet("/booksAdd")
@MultipartConfig
public class BooksAddServlet extends HttpServlet {

    private final BookService bookService = ApplicationContextHolder.getBean(BookService.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String genre = req.getParameter("genre");
        String language = req.getParameter("language");
        int pageCount = Integer.parseInt(req.getParameter("pageCount"));
        Part cover = req.getPart("cover");
        Part file = req.getPart("file");
        String description = req.getParameter("description");
        BookCreateDTO bookCreateDTO = BookCreateDTO
                .builder()
                .name(name)
                .author(author)
                .genre(genre)
                .language(language)
                .description(description)
                .pageCount(pageCount)
                .cover(cover)
                .file(file)
                .build();
        bookService.save(bookCreateDTO);
        resp.sendRedirect("/");
    }
}
