package com.syberry.server.controller;

import com.syberry.server.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@Slf4j
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            ProductNotFoundException.class,
            TypeNotFoundException.class,
            OrderNotFoundException.class,
            BrandNotFoundException.class,
            UsernameNotFoundException.class,
            WishlistNotFoundException.class,
            CartNotFoundException.class
    })
    public ResponseEntity<Map<String, String>> handleNotFoundExceptions(Exception e) {
        return new ResponseEntity<>(Map.of("message", e.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            BrandAlreadyExistsException.class,
            ProductAlreadyExistsException.class,
            ProductExistsInCartException.class,
            ProductExistsInWishlistException.class,
            TypeAlreadyExistsException.class
    })
    public ResponseEntity<Map<String, String>> handleAlreadyExistsExceptions(Exception e) {
        return new ResponseEntity<>(Map.of("message", e.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
