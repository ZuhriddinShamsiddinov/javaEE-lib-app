package com.example.libapp.exceptions;

import javax.servlet.ServletException;

public class InvalidInputException extends ServletException {
    public InvalidInputException(String message) {
        super(message);
    }
}
