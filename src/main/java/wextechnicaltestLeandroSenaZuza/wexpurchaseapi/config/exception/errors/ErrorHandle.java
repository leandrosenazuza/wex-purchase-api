package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Data
public class ErrorHandle extends RuntimeException{
    private String parameter;
    private String message;
    private int status;
    private final OffsetDateTime timestamp = OffsetDateTime.now();
}
