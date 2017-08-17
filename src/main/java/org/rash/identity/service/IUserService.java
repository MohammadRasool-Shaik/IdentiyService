/**
 * 
 */
package org.rash.identity.service;

import java.util.List;

import org.jose4j.lang.JoseException;
import org.rash.identity.dto.AuthenticatedUserToken;
import org.rash.identity.dto.LoginDTO;
import org.rash.identity.dto.ResponseStatus;
import org.rash.identity.model.User;

/**
 * @author mshai9
 *
 */
public interface IUserService {

	public ResponseStatus creatUser(User user);

	public AuthenticatedUserToken login(LoginDTO request) throws JoseException;

	public List<User> fetchAllUsers();
}
