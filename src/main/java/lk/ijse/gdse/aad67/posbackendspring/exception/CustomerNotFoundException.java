package lk.ijse.gdse.aad67.posbackendspring.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
