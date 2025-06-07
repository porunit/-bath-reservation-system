package poruit.bathbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poruit.bathbooking.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
    // Стандартные CRUD-методы
}
