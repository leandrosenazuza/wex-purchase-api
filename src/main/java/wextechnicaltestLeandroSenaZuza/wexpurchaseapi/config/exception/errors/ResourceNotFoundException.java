package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ResourceNotFoundException extends RuntimeException {
      public ResourceNotFoundException(String message) {
          super(message);
    }
}