package abhijeet.java.com.example.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import abhijeet.java.com.example.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
