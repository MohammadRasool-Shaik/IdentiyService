/**
 * 
 */
package org.rash.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.rash.identity.model.User;

/**
 * @author mshai9
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}
