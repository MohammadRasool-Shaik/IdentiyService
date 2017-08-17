/**
 * 
 */
package org.rash.identity.provider;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.rash.identity.dto.ErrorResponse;
import org.rash.identity.exception.NotFoundException;

/**
 * @author mshai9
 *
 */
@Provider
public class ResourceNotFoundMapper implements ExceptionMapper<NotFoundException> {

	private static Logger logger = LoggerFactory.getLogger(ResourceNotFoundMapper.class.getName());

	@Override
	public Response toResponse(NotFoundException exception) {
		Response.StatusType statusType = getStatusType(exception);

		ErrorResponse errorResponse = new ErrorResponse(statusType.getStatusCode(), statusType.getReasonPhrase());
		logger.error("Internal Server Error: " + exception.getCause(),exception);

		return Response.status(statusType.getStatusCode()).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
	}

	private Response.StatusType getStatusType(Exception ex) {
		if (ex instanceof NotFoundException) {
			return Response.Status.NOT_FOUND;
		} else if (ex instanceof WebApplicationException) {
			return ((WebApplicationException) ex).getResponse().getStatusInfo();
		} else {
			return Response.Status.INTERNAL_SERVER_ERROR;
		}
	}

}
