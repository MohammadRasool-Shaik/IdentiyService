/**
 * 
 */
package org.rash.identity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author mshai9
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IdentityServerBootStart.class)
public class UserServiceTest {
	
	//same user exists or not
	@Test
	public void testUserExists(){
		
	}
}
