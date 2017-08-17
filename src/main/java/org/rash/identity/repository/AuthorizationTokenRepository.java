/**
 * 
 */
package org.rash.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.rash.identity.model.AuthorizationToken;

/**
 * @author mshai9
 *
 */
public interface AuthorizationTokenRepository extends JpaRepository<AuthorizationToken, Long> {

	@Query("select t from AuthorizationToken t where token = ?")
	AuthorizationToken findByToken(String token);
}
