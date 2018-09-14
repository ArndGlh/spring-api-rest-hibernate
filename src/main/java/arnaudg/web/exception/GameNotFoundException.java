package arnaudg.web.exception;

public final class GameNotFoundException extends RessourceNotFoundException{

    public GameNotFoundException() {
        super();
    }

    public GameNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public GameNotFoundException(final String message) {
        super(message);
    }

    public GameNotFoundException(final Throwable cause) {
        super(cause);
    }
}