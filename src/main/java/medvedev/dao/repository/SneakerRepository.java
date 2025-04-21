package medvedev.dao.repository;

import java.util.List;
import medvedev.dao.entities.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface SneakerRepository extends JpaRepository<Sneaker, Long> {

    // Кастомный запрос для поиска всех кроссовок по бренду
    @Query("SELECT s FROM Sneaker s WHERE s.brand = :brand")
    List<Sneaker> findSneakersByBrand(@Param("brand") String brand);
}
