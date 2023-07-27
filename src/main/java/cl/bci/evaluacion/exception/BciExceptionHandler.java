package cl.bci.evaluacion.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@Slf4j
@ControllerAdvice
public class BciExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BciException> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(String.format("Bad request: %s.", e.getMessage()));
        return ResponseEntity.badRequest().body(BciException.builder()
                .message(e.getMessage())
                .build());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<BciException> handleEntityNotFoundException(EntityNotFoundException e) {
        log.error(String.format("Not found: %s.", e.getMessage()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(BciException.builder()
                        .message(e.getMessage())
                        .build());
    }
}
