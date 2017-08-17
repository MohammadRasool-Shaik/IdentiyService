package org.rash.identity.resource;

import java.net.URI;
import java.util.Calendar;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;

import org.rash.identity.dto.AuthenticatedUserToken;
import org.rash.identity.dto.LoginDTO;
import org.rash.identity.dto.ResponseStatus;
import org.rash.identity.model.User;
import org.rash.identity.service.IUserService;

/**
 * 
 * @author mshai9
 *
 */

@Path("/users")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
public class UserResource {

	@Autowired
	public IUserService userService;

	@Context
	protected UriInfo uriInfo;

	/*@GET
	@PermitAll
	public Response ping() {
		return Response.ok().entity("Running version " + Calendar.getInstance().getTime()).build();
	}*/

	@PermitAll
	@POST
	public ResponseStatus registerUser(User user) {
		return userService.creatUser(user);
	}

	@PermitAll
	@Path("login")
	@POST
	public AuthenticatedUserToken login(LoginDTO request, @Context SecurityContext securityContext) throws JoseException {
		AuthenticatedUserToken token = userService.login(request);
		return token;
	}

	private Response getLoginResponse(AuthenticatedUserToken token, SecurityContext securityContext) {
		URI location = UriBuilder.fromPath(uriInfo.getBaseUri() + "users/" + token.getEmail()).build();
		return Response.ok().entity(token).contentLocation(location).build();
	}

	@GET

	public List<User> fetchUsers(){
		return userService.fetchAllUsers();
	}

}
