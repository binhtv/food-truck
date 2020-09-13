package food.truck.exception;

public class ThrottlingLimitsException extends Exception {
    public ThrottlingLimitsException(String message) {
        super(message);
    }

    public ThrottlingLimitsException(String message, Exception e) {
        super(message, e);
    }
}
