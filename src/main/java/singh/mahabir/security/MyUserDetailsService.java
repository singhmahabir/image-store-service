/**
 * All rights reserved.
 */

package singh.mahabir.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import singh.mahabir.security.repository.MyUserDetails;
import singh.mahabir.security.repository.User;
import singh.mahabir.security.repository.UserRepository;

/**
 * @author Mahabir Singh
 *
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	Optional<User> user = userRepository.findByUserName(username);

	user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

	return user.map(MyUserDetails::new).get();
    }

}
