package com.example.libapp.servlets;

import com.example.libapp.configs.ApplicationContextHolder;
import com.example.libapp.dao.BookDAO;
import com.example.libapp.domain.Book;
import com.example.libapp.domain.User;
import com.example.libapp.enums.Language;
import com.example.libapp.service.BookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@WebServlet(value = "/")
public class HomeServlet extends HttpServlet {
    private final BookDAO bookDAO = ApplicationContextHolder.getBean(BookDAO.class);
    private final BookService bookService = ApplicationContextHolder.getBean(BookService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User authSession = (User) session.getAttribute("auth_session");
        if (Objects.nonNull(authSession) && authSession.getRole().equals(User.Role.USER)) {
            String search = req.getParameter("search");
            if (search == null) {
                search = "";
            }
            int page = 1;
            int recordsPerPage = 5;
            if (req.getParameter("page") != null)
                page = Integer.parseInt(
                        req.getParameter("page"));
            List<Book> list = bookDAO.viewAllBooks(
                    (page - 1) * recordsPerPage,
                    recordsPerPage, search);

            req.setAttribute("books", list);
            req.setAttribute("noOfPages", (list.size() / recordsPerPage));
            req.setAttribute("currentPage", page);
            req.setAttribute("username", req.getSession().getAttribute("auth_session"));
            req.setAttribute("search", search);
//            req.setAttribute("books", bookService.getAllForUsers());
            req.setAttribute("genres", Book.Genre.values());
            req.setAttribute("languages", Language.values());
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/main.jsp");
            dispatcher.forward(req, resp);
        } else if (Objects.nonNull(authSession) && authSession.getRole().equals(User.Role.ADMIN)) {
            String search = req.getParameter("search");
            if (search == null) {
                search = "";
            }
            int page = 1;
            int recordsPerPage = 5;
            if (req.getParameter("page") != null)
                page = Integer.parseInt(
                        req.getParameter("page"));
            List<Book> list = bookDAO.findAll(
                    (page - 1) * recordsPerPage,
                    recordsPerPage, search);

            req.setAttribute("books", list);
            req.setAttribute("noOfPages", (list.size() / recordsPerPage));
            req.setAttribute("currentPage", page);
            req.setAttribute("username", req.getSession().getAttribute("auth_session"));
//            req.setAttribute("books", bookService.getAllForAdmin());
            req.setAttribute("genres", Book.Genre.values());
            req.setAttribute("languages", Language.values());
            RequestDispatcher dispatcher = req.getRequestDispatcher("views/auth/admin.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("/login");
        }
    }
}