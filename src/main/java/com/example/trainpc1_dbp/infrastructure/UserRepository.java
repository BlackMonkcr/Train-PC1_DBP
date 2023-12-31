package com.example.trainpc1_dbp.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.trainpc1_dbp.domain.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
