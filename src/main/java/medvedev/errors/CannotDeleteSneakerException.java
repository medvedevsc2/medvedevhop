package medvedev.errors;

public class CannotDeleteSneakerException extends RuntimeException { // Updated to reflect sneakers
    public CannotDeleteSneakerException(String message) {
        super(message);
    }
}
