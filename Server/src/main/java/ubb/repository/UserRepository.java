package ubb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ubb.model.User;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User,Long> {
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
}
