package com.clearout.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorDetail> handleBusinessException(BusinessException e, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EWasteAgentException.class)
    public ResponseEntity<ErrorDetail> handleEWasteAgentException(EWasteAgentException e, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EWasteRequestException.class)
    public ResponseEntity<ErrorDetail> handleEWasteRequestException(EWasteRequestException e, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IndividualUserException.class)
    public ResponseEntity<ErrorDetail> handleIndividualUserException(IndividualUserException e, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ErrorDetail> handleProductException(ProductException e, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RepairAgentException.class)
    public ResponseEntity<ErrorDetail> handleRepairAgentException(RepairAgentException e, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(RepairRequestException.class)
    public ResponseEntity<ErrorDetail> handleRepairRequestException(RepairRequestException e, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail(e.getMessage(),request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }

}
