package pl.mazur.pawel.Family.exceptions;


import java.util.function.Supplier;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public static Supplier<BusinessException> businessException(Statements message) {
        return () -> new BusinessException(message.toString());
    }
}
