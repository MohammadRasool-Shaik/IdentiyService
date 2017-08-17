/**
 * 
 */
package org.rash.identity.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.rash.identity.dto.ErrorResponse;
import org.rash.identity.dto.ValidationError;

/**
 * @author mshai9
 *
 */
public class ValidationException extends WebApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8037355625812982679L;
	/**
	 * 
	 */

	private static final int STATUS = 400;
	private final String errorMessage;
	private final List<ValidationError> errors = new ArrayList<>();

	public ValidationException() {
		errorMessage = "Validation Error";
	}

	public ValidationException(String message) {
		super();
		errorMessage = message;
	}

	public ValidationException(Set<? extends ConstraintViolation<?>> violations) {
		this();
		for (ConstraintViolation<?> constraintViolation : violations) {
			ValidationError error = new ValidationError();
			error.setMessage(constraintViolation.getMessage());
			error.setPropertyName(constraintViolation.getPropertyPath().toString());
			error.setPropertyValue(constraintViolation.getInvalidValue() != null ? constraintViolation.getInvalidValue().toString() : null);
			errors.add(error);
		}
	}

	@Override
	public Response getResponse() {
		return Response.status(STATUS).type(MediaType.APPLICATION_JSON_TYPE).entity(new ErrorResponse(STATUS, errorMessage)).build();
	}

}
