package rayan.userservice.core.excpetion;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import rayan.userservice.dto.ErrorMessageDTO;

@Provider
public class AppExceptionMapper  implements ExceptionMapper<GenericException> {

    @Override
    public Response toResponse(GenericException exception) {
        // Default status
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        if (exception instanceof EntityInvalidArgumentsException) {
            status = Response.Status.BAD_REQUEST;
        } else if (exception instanceof EntityAlreadyExistsException) {
            status = Response.Status.CONFLICT;
        } else if (exception instanceof EntityNotFoundException) {
            status = Response.Status.NOT_FOUND;
        } else if (exception instanceof AppServerException) {
            status = Response.Status.SERVICE_UNAVAILABLE;
        }
        return Response
                .status(status)
                .entity(new ErrorMessageDTO(exception.getCode(), exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
