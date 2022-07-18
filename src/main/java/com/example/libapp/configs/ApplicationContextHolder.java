package com.example.libapp.configs;

import com.example.libapp.dao.BookDAO;
import com.example.libapp.dao.UploadsDAO;
import com.example.libapp.dao.auth.UserDAO;
import com.example.libapp.service.BookService;
import com.example.libapp.service.FileUploadsService;

public class ApplicationContextHolder {
    @SuppressWarnings("unchecked")
    private static <T> T getBean(String beanName) {
        return switch (beanName) {
            case "UserDAO" -> (T) UserDAO.getInstance();
            case "BookDAO" -> (T) BookDAO.getInstance();
            case "BookService" -> (T) BookService.getInstance();
            case "FileUploadsService" -> (T) FileUploadsService.getInstance();
            case "UploadsDAO" -> (T) UploadsDAO.getInstance();
            default -> throw new BeanNotFoundException("Bean Not Found");
        };
    }

    public static <T> T getBean(Class<T> clazz) {
        String beanName = clazz.getSimpleName();
        return getBean(beanName);
    }

    public static class BeanNotFoundException extends RuntimeException {
        public BeanNotFoundException(String message) {
            super(message);
        }
    }
}

