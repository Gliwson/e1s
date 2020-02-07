package io.github.e1s.e1s.controllers.errors;

public class NotFoundDiscountException extends RuntimeException {
    public NotFoundDiscountException(String type) {
        super("Could not find discount " + type);
    }
}
