package com.example.EurekaClient.controllers;

import com.google.gson.Gson;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandler implements ErrorController {
    @RequestMapping("/error")
    @ResponseBody
    public ResponseEntity<String> handleError(HttpServletRequest request) {

        Integer status_code = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        String result = String.format(
                        "Status code: %s \n" +
                        "Message: %s",
                status_code,
                exception==null ? "Unknown error" : exception.getMessage().split("Exception: ")[1]
        );
        HttpStatus code = status_code < 500 ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<String>(result, code);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
