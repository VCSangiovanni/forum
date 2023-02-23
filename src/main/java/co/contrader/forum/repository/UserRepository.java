package co.contrader.forum.repository;

import co.contrader.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByActivationCode(String activationCode);

    Optional<User> findByUsername(String username);

    Optional<User> findByResetPasswordCode(String resetPasswordCode);

    User deleteByUsername(String username);
}
