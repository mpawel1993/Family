package pl.mazur.pawel.Family.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FamilyControllerAdvice {

    private static final String ERROR_OCCURRED = "An_unexpected_error_occurred : ";
    private static final String VALIDATION_NOT_PASSED = "Provide_correct_data";

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e) {
        e.printStackTrace();
        return ERROR_OCCURRED + e.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String exceptionHandler(MethodArgumentNotValidException e) {
        e.printStackTrace();
        return VALIDATION_NOT_PASSED;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public String runtimeExceptionHandler(RuntimeException e) {
        e.printStackTrace();
        return e.getMessage();
    }
}
