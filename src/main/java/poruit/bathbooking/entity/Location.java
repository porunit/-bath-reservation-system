package poruit.bathbooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "locations")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;        // Название локации (напр. "Центральный район")

    private String address;     // Полный адрес (опционально)

    @Column(nullable = false)
    private String city;        // Город

    private String region;      // Регион/область (опционально)

    // Координаты (опционально)
    private Double latitude;
    private Double longitude;
}
