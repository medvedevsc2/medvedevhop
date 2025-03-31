package medvedev.dao.repository;

import medvedev.dao.entities.Order;
import medvedev.dao.entities.Client;
import io.micrometer.common.lang.NonNull;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"sneakers", "client"}) // Загружаем и sneakers, и client
    @NonNull
    @Override
    List<Order> findAll();

    @EntityGraph(attributePaths = {"sneakers", "client"})
    Optional<Order> findWithSneakersById(Long id);

    List<Order> findByClient(Client client);
}