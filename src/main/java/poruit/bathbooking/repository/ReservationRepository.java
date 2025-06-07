package poruit.bathbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poruit.bathbooking.entity.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Ищет пересекающиеся бронирования для конкретной бани:
     * Возвращает все записи, у которых:
     *   startDateTime < end (запрошенного интервала)  И 
     *   endDateTime   > start (запрошенного интервала)
     */
    List<Reservation> findByBathhouseIdAndStartDateTimeLessThanAndEndDateTimeGreaterThan(
            Long bathhouseId, LocalDateTime end, LocalDateTime start);
}
