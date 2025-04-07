package medvedev.dao.repository;

import java.util.Optional;
import medvedev.dao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    // Use Optional<User> instead of List<User>
    Optional<Client> findById(Long id);

    boolean existsByEmail(String email);
}
