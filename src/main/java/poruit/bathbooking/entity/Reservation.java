package poruit.bathbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservations")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ссылка на баню
    @ManyToOne
    @JoinColumn(name = "bathhouse_id", nullable = false)
    private Bathhouse bathhouse;

    // Ссылка на пользователя
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

    @Column(name = "start_datetime", nullable = false)
    private LocalDateTime startDateTime;  // Начало бронирования

    @Column(name = "end_datetime", nullable = false)
    private LocalDateTime endDateTime;    // Конец бронирования

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Проверка целостности: start < end
     */
    @PrePersist
    @PreUpdate
    private void validateInterval() {
        if (startDateTime == null || endDateTime == null || !startDateTime.isBefore(endDateTime)) {
            throw new IllegalArgumentException("startDateTime должен быть раньше endDateTime");
        }
    }
}
