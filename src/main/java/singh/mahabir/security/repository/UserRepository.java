/**
 * All rights reserved.
 */

package singh.mahabir.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mahabir Singh
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
}