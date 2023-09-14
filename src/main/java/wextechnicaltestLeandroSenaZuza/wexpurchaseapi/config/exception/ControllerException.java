package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerException {

        @ResponseStatus(HttpStatus.NOT_FOUND)
        @ExceptionHandler(ResourceNotFoundException.class)
        public ErrorHandle handleResourceNotFoundException(ResourceNotFoundException exception) {
            return new ErrorHandle(exception.getMessage(), HttpStatus.NOT_FOUND);
        }

        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        @ExceptionHandler(PersistErrorException.class)
        public  ResponseEntity<ErrorHandle>  handlePrimaryKeyViolationException(PersistErrorException exception) {
            ErrorHandle errorResponse = new ErrorHandle(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        @ExceptionHandler(ConvertException.class)
        public  ResponseEntity<ErrorHandle>  handleConvertException(ConvertException exception) {
            ErrorHandle errorResponse = new ErrorHandle(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotFoundException.class)
    public  ResponseEntity<ErrorHandle>  handleEmptyTable(NotFoundException exception) {
        ErrorHandle errorResponse = new ErrorHandle(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }



    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DecimalFormatException.class)
    public  ResponseEntity<ErrorHandle>  handleConvertToDouble(DecimalFormatException exception) {
        ErrorHandle errorResponse = new ErrorHandle(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    }

