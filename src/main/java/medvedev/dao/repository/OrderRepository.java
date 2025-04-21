package medvedev.dao.repository;


import java.util.List;
import java.util.Optional;
import medvedev.dao.entities.Client;
import medvedev.dao.entities.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"sneakers", "client"})
    List<Order> findAll();

    @EntityGraph(attributePaths = {"sneakers", "client"})
    Optional<Order> findWithSneakersById(Long id);

    List<Order> findByClient(Client client);

    // Кастомный запрос для фильтрации заказов по бренду кроссовок
    @Query("SELECT o FROM Order o JOIN o.sneakers s WHERE s.brand = :brand")
    List<Order> findOrdersBySneakerBrand(@Param("brand") String brand);
}
