package cl.bci.evaluacion.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class BciExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BciException> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(BciException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(e.getMessage())
                .build());
    }
}
