package com.example.libapp.servlets.auth;

import com.example.libapp.configs.ApplicationContextHolder;
import com.example.libapp.configs.PasswordEncoder;
import com.example.libapp.dao.auth.UserDAO;
import com.example.libapp.domain.User;
import com.example.libapp.exceptions.InvalidInputException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


@WebServlet("/register")
public class AuthRegisterServlet extends HttpServlet {

    private final UserDAO userDao = ApplicationContextHolder.getBean(UserDAO.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("views/auth/register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = userDao.findByUsername(username);
        if (Objects.nonNull(user))
            throw new InvalidInputException("'%s' Username already taken".formatted(username));
        user = User.builder()
                .username(username)
                .password(PasswordEncoder.encode(password))
                .build();
        userDao.save(user);

        resp.sendRedirect("/login");
    }


}

