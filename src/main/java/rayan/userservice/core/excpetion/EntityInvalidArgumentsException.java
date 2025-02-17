package rayan.userservice.core.excpetion;

public class EntityInvalidArgumentsException extends GenericException {
    private static final String DEFAULT_CODE = "InvalidArguments";

    public EntityInvalidArgumentsException(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}
