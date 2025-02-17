package rayan.userservice.core.excpetion;

public class AppServerException extends GenericException {
    private static final String DEFAULT_CODE = "AppServerError";

    public AppServerException(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}
