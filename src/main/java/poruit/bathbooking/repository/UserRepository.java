package poruit.bathbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poruit.bathbooking.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByTgUsername(String tgUsername);
}
