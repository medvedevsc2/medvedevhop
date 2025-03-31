package medvedev.dao.repository;

import medvedev.dao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Use Optional<User> instead of List<User>
    Optional<Client> findById(Long id); // This will work with the inherited findById from JpaRepository

    boolean existsByEmail(String email);
}
