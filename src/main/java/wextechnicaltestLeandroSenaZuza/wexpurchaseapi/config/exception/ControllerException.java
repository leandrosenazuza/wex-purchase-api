package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.*;

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

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(BadParamRequestException.class)
        public  ResponseEntity<ErrorHandle>  handleConvertException(BadParamRequestException exception) {
            ErrorHandle errorResponse = new ErrorHandle(exception.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    @ResponseStatus(HttpStatus.NOT_FOUND)
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

