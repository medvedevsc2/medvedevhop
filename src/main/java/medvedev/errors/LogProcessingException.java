package medvedev.errors;

public class LogProcessingException extends RuntimeException {
    public LogProcessingException(String message) {
        super(message);
    }
}