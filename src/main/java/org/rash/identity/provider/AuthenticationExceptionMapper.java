/**
 * 
 */
package org.rash.identity.provider;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.rash.identity.dto.ErrorResponse;
import org.rash.identity.exception.AuthenticationException;

/**
 * @author mshai9
 *
 */
@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {
	private static Logger logger = LoggerFactory.getLogger(AuthenticationExceptionMapper.class.getName());

	@Override
	public Response toResponse(AuthenticationException exception) {

		ErrorResponse errorResponse = new ErrorResponse(Response.Status.UNAUTHORIZED.getStatusCode(), exception.getMessage());

		logger.error("Internal Server Error: " + exception.getCause(),exception);

		return Response.status(errorResponse.getErrorCode()).entity(errorResponse).type(MediaType.APPLICATION_JSON).build();
	}

}
