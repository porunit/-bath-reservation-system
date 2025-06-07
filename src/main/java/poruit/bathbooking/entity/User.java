package poruit.bathbooking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;     // Полное имя

    private String phone;        // Телефон (опционально)

    @Column(name = "tg_username", nullable = false, unique = true)
    private String tgUsername;   // Telegram-username (уникальный)
}
