package com.example.gradesprojectbackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalErroHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<CustomErrorResponse> handleAllException(Exception ex, WebRequest req) {
//        CustomErrorResponse er = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
//        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleModelNotFoundException(ModelNotFoundException ex, WebRequest req) {
        CustomErrorResponse er = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<DniResponse> handleModelNotFoundException(Exception ex, WebRequest req) {
//        String message = ex.getMessage().replace("404 Not Found: \"{\"message\":\"not found\"}\"", "dni no válido");
//        DniResponse response = new DniResponse(message);
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<CustomErrorResponse> handleAllException(SQLException ex, WebRequest req) {
        CustomErrorResponse er = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.CONFLICT);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
        CustomErrorResponse er = new CustomErrorResponse(LocalDateTime.now(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    //Devuelve los campos en los que no se cumple los constraints del DTO
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest req) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        CustomErrorResponse er = new CustomErrorResponse(LocalDateTime.now(), message, req.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);
    }

    //Desde Spring Boot 3.0
//    @ExceptionHandler(ModelNotFoundException.class)
//    public ProblemDetail handleModelNotFoundException(ModelNotFoundException ex, WebRequest req) {
//        ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
//        pd.setTitle("Model Not Found Exception");
//        pd.setType(URI.create(req.getContextPath()));
//        pd.setProperty("add-var1", "value1");
//        pd.setProperty("add-var2", true);
//        return pd;
//    }
}
