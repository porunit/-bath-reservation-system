package poruit.bathbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import poruit.bathbooking.entity.Bathhouse;

import java.time.LocalDateTime;
import java.util.List;

public interface BathhouseRepository extends JpaRepository<Bathhouse, Long> {

    /**
     * Находит все бани, которые **не** имеют пересечений с бронированиями
     * на заданный интервал [start, end). Если у бани нет ни одного
     * бронирования, пересекающегося с этим интервалом, она считается свободной.
     */
    @Query("SELECT b " +
            "FROM Bathhouse b " +
            "WHERE NOT EXISTS (" +
            "  SELECT r FROM Reservation r " +
            "  WHERE r.bathhouse = b " +
            "    AND r.startDateTime < :end " +
            "    AND r.endDateTime > :start" +
            ")")
    List<Bathhouse> findAvailableBathhouses(
            @Param("start") LocalDateTime start,
            @Param("end")   LocalDateTime end
    );
}
