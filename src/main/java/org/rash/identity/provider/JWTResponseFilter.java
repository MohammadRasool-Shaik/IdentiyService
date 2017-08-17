/**
 * 
 */
package org.rash.identity.provider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Priority;
import javax.annotation.security.PermitAll;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import org.jose4j.lang.JoseException;
import org.rash.identity.util.JWTokenUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mshai9
 * 
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTResponseFilter implements ContainerResponseFilter {

	private static final Logger logger = LoggerFactory.getLogger(JWTResponseFilter.class.getName());

	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		Method method = resourceInfo.getResourceMethod();
		if (method!=null && !method.isAnnotationPresent(PermitAll.class)) {
			logger.info("response filter invoked...");

			List<Object> jwt = new ArrayList<Object>();
			try {
				requestContext.getSecurityContext();
				jwt.add("Bearer " + JWTokenUtility.buildJWT("email1@mail.com"));
			} catch (JoseException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			// jwt.add(requestContext.getHeaderString("Authorization"));
			responseContext.getHeaders().put("jwt", jwt);
			logger.info("Added JWT to response header 'jwt'");
		}
	}

	

}