package medvedev.dao.repository;

import java.util.List;
import java.util.Optional;
import medvedev.dao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findById(Long id);
    @SuppressWarnings("checkstyle:EmptyLineSeparator")//test
    boolean existsByEmail(String email);

    @Query("SELECT DISTINCT c FROM Client c JOIN c.orders o JOIN"
            + " o.sneakers s WHERE s.brand = :brand")
    List<Client> findClientsBySneakerBrand(@Param("brand") String brand);
}
