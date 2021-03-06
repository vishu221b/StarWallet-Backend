package com.StarWallet.StarWallet.controller;

import com.StarWallet.StarWallet.model.dto.ExceptionDTO;
import com.StarWallet.StarWallet.model.exceptions.StarWalletInternalServerErrorException;
import com.StarWallet.StarWallet.model.exceptions.StarWalletResourceAlreadyExistsException;
import com.StarWallet.StarWallet.model.exceptions.StarWalletResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.Date;

@Slf4j
@ControllerAdvice
@RestController
public class ExceptionController {

    @ExceptionHandler(value = {StarWalletInternalServerErrorException.class, Exception.class})
    public ResponseEntity<ExceptionDTO> handleException(Exception ex, WebRequest request){
        log.error("Exception: " + ex.getMessage());
        log.error("StackTrace: " + Arrays.toString(ex.getStackTrace()));
        ex.printStackTrace();
        return new ResponseEntity<>(new ExceptionDTO(ex.getMessage(),
                (ex.getClass().isInstance(StarWalletInternalServerErrorException.class))
                    ? ((StarWalletInternalServerErrorException)ex).getEpoch() : new Date().getTime()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = StarWalletResourceAlreadyExistsException.class)
    public ResponseEntity<ExceptionDTO> handleException(StarWalletResourceAlreadyExistsException ex, WebRequest request){
        log.error("Exception: " + ex.getMessage());
        log.error("StackTrace: " + Arrays.toString(ex.getStackTrace()));
        ex.printStackTrace();
        return new ResponseEntity<>(new ExceptionDTO(ex.getMessage(),ex.getEpoch()), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = StarWalletResourceNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleException(StarWalletResourceNotFoundException ex, WebRequest request){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setEpoch(ex.getEpoch());
        exceptionDTO.setErrorMessage(ex.getMessage());
        log.error("Exception: " + ex.getMessage());
        log.error("StackTrace: " + Arrays.toString(ex.getStackTrace()));
        ex.printStackTrace();
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
