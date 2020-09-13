package food.truck.exception;

public class FailureToFetchDataException extends Exception {
    public FailureToFetchDataException(String message) {
        super(message);
    }

    public FailureToFetchDataException(String message, Exception e) {
        super(message, e);
    }
}
