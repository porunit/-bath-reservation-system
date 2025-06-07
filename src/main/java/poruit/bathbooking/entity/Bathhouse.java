package poruit.bathbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bathhouses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bathhouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ссылка на локацию
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column(nullable = false)
    private String name;        // Название бани

    @Column(columnDefinition = "TEXT")
    private String description; // Описание

    @Column(nullable = false)
    private Integer capacity;   // Вместимость

    @Column(name = "price_per_hour", nullable = false)
    private BigDecimal pricePerHour; // Стоимость за час

    // Метки времени
    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
