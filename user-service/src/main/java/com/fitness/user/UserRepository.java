
package com.fitness.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Long>{
    Optional<Users> findByEmail(String email);
    Optional<Users> findByEmailIgnoreCase(String email);

    boolean existsByEmail(String email);
}
