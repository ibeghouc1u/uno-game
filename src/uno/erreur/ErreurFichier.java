package uno.erreur;

public class ErreurFichier extends Exception {
    public ErreurFichier(String message) {
        super(message);
    }

    public ErreurFichier(String message, Throwable cause) {
        super(message, cause);
    }
}

