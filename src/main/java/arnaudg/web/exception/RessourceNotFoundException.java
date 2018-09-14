package arnaudg.web.exception;

public class RessourceNotFoundException extends RuntimeException{

    public RessourceNotFoundException(){

    }

    public RessourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RessourceNotFoundException(final String message) {
        super(message);
    }

    public RessourceNotFoundException(final Throwable cause) {
        super(cause);
    }
}
