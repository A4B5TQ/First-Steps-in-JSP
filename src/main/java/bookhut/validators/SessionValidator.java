package bookhut.validators;

public class SessionValidator implements Validator {
    private static SessionValidator validator = new SessionValidator();

    public static SessionValidator getInstance() {
        return validator;
    }

    private SessionValidator() {

    }
}
