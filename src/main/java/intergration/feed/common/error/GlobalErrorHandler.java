package intergration.feed.common.error;

import intergration.feed.common.error.wanted.WantedException;
import intergration.feed.common.error.wanted.WantedExceptionResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> getValidationResponse(MethodArgumentNotValidException e) {
        Map<String, String> error = new HashMap<>();
        e.getAllErrors().forEach(errorElement ->
            error.put(((FieldError)errorElement).getField(),errorElement.getDefaultMessage())
        );
        return error;
    }

    @ExceptionHandler({WantedException.class})
    public ResponseEntity<WantedExceptionResponse> getPetionaryExceptionResponse(WantedException exception) {
        return ResponseEntity.status(exception.getStatus())
            .body(WantedExceptionResponse.responseWantedException(exception));
    }
}
