package com.example.libapp.servlets;

import com.example.libapp.configs.ApplicationContextHolder;
import com.example.libapp.service.FileUploadsService;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author - 'Zuhriddin Shamsiddionov' at 3:45 PM 7/14/22 on Thursday in July
 */
@WebServlet("/download")
public class DownloadFilesServlet extends HttpServlet {
    private final FileUploadsService fileUploadsService = ApplicationContextHolder.getBean(FileUploadsService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String fileName = req.getParameter("path");
        String filePath = "/home/zuhriddin/IdeaProjects/javaee/uploads/" + fileName;
        File downloadFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        ServletContext context = getServletContext();

        String contentType = context.getMimeType(filePath);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        resp.setContentType(contentType);
        resp.setContentLength((int) downloadFile.length());

        String originalName = fileUploadsService.getOriginalName(downloadFile.getName());

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", originalName);
        resp.setHeader(headerKey, headerValue);

        OutputStream outputStream = resp.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();
    }
}
