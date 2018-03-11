package com.wk.springbootapp.app.common.config;

import com.google.common.collect.Lists;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.List;

/**
 * 处理全局异常
 */
@RestControllerAdvice
public class ControllerConfig {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            ConstraintViolationException.class,
            MissingServletRequestParameterException.class,
            HttpMediaTypeException.class
    })
    public String invalidRequest(HttpServletRequest req, Exception e){
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException ex = (ConstraintViolationException) e;
            List<ConstraintViolation> violations = Lists.newArrayList(ex.getConstraintViolations());
            return String.join("\n", Lists.transform(violations, ConstraintViolation::getMessage));
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            return String.join("\n", Lists.transform(ex.getBindingResult().getFieldErrors(),
                    (fieldError) -> String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage())));
        }
        return "invalid request";
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataRetrievalFailureException.class)
    public String dateNotFound() {
        return "data not found";
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public String databaseError() {
        return "database error";
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    public String error(HttpServletRequest req, Exception ex) {
        ex.printStackTrace();
        return "unknown internal error";
    }
}
