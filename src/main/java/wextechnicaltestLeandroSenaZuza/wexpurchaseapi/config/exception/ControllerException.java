package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors.ErrorHandle;

@RestControllerAdvice
public class ControllerException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ErrorHandle.class)
    public ErrorHandle handleExceptionBody(ErrorHandle exception) {
        return new ErrorHandle(exception.getParameter(), exception.getMessage(), 400);
    }
}
