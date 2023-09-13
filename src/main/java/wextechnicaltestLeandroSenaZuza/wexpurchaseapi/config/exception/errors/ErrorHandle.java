package wextechnicaltestLeandroSenaZuza.wexpurchaseapi.config.exception.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;


@Getter
public class ErrorHandle {
    private String message;
    private HttpStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS")
    private final OffsetDateTime timestamp = OffsetDateTime.now();

    public ErrorHandle(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
