package com.example.libapp.servlets.auth;

import com.example.libapp.configs.ApplicationContextHolder;
import com.example.libapp.configs.PasswordEncoder;
import com.example.libapp.dao.auth.UserDAO;
import com.example.libapp.domain.User;
import com.example.libapp.exceptions.AuthenticationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/login")
public class AuthLoginServlet extends HttpServlet {
    private final UserDAO userDao = ApplicationContextHolder.getBean(UserDAO.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/auth/login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userDao.findByUsername(username);

        if (Objects.isNull(user) || !PasswordEncoder.match(password, user.getPassword())) {
            throw new AuthenticationException("Bad Credentials");
        }

        HttpSession session = req.getSession();
        session.setAttribute("auth_session", user);
        resp.sendRedirect("/");
    }
}
