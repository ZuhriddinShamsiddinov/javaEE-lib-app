package com.example.libapp.servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 12:14 PM 7/14/22 on Thursday in July
 */
@WebServlet("/display")
public class DisplayImageServlet extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        String requestedImage = request.getParameter("img");
        Path path = Paths.get("/home/zuhriddin/IdeaProjects/javaee/uploads/", requestedImage);
        ServletOutputStream outputStream = response.getOutputStream();
        Files.copy(path, outputStream);
    }
}
