package io.github.e1s.components.discount.errors;

public class NotFoundDiscountException extends RuntimeException {
    public NotFoundDiscountException(String type) {
        super("Could not find discount " + type);
    }
}
