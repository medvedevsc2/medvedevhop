package medvedev.errors;

public class ErrorMessages {
    private ErrorMessages() {
        throw new UnsupportedOperationException(
                "This is a utility class and cannot be instantiated");
    }

    public static final String SNEAKER_IN_USE = "Sneaker with id %d cannot be "
            + "deleted because it is used in orders";
    public static final String ERROR = "error";
    public static final String USER_NOT_FOUND = "User with id %d not found";
    public static final String ORDER_NOT_FOUND = "Order with id %d not found";
    public static final String SNEAKER_NOT_FOUND = "Sneaker with id %d not found";
    public static final String EMAIL_EXISTS = "User with email %s already exists";
    public static final String NO_VALID_SNEAKERS = "No valid sneakers found for the given IDs";
    public static final String NO_SNEAKERS_FOUND_BY_BRAND =
            "No sneakers found with the brand '%s'!";
}
