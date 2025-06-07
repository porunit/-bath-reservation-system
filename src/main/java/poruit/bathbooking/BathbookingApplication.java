package poruit.bathbooking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import poruit.bathbooking.entity.Bathhouse;
import poruit.bathbooking.entity.Location;
import poruit.bathbooking.entity.User;
import poruit.bathbooking.repository.BathhouseRepository;
import poruit.bathbooking.repository.LocationRepository;
import poruit.bathbooking.repository.UserRepository;

import java.math.BigDecimal;

@SpringBootApplication
public class BathbookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BathbookingApplication.class, args);
    }

    /**
     * Инициализируем тестовые данные для локаций и бань.
     */
    @Bean
    CommandLineRunner initData(LocationRepository locationRepo, BathhouseRepository bathhouseRepo, UserRepository userRepo) {
        return args -> {

            User user = new User(
                    null,
                    "hui",
                    "porunit",
                    "porunit"
            );
            // Локация 1
            userRepo.save(user);
            Location loc1 = Location.builder()
                    .name("Центральный район")
                    .address("ул. Ленина, д. 10")
                    .city("Таллинн")
                    .region("Харьюмаа")
                    .latitude(59.4370)
                    .longitude(24.7536)
                    .build();
            locationRepo.save(loc1);

            // Локация 2
            Location loc2 = Location.builder()
                    .name("Пирита")
                    .address("ул. Пирита, д. 5")
                    .city("Таллинн")
                    .region("Харьюмаа")
                    .latitude(59.4669)
                    .longitude(24.8603)
                    .build();
            locationRepo.save(loc2);

            // Баня 1 (привязана к loc2)
            Bathhouse bath1 = Bathhouse.builder()
                    .name("Уютная баня у моря")
                    .description("Баня с видом на море, вместимость до 6 человек")
                    .capacity(6)
                    .pricePerHour(new BigDecimal("30.00"))
                    .location(loc2)
                    .build();
            bathhouseRepo.save(bath1);

            // Баня 2 (привязана к loc1)
            Bathhouse bath2 = Bathhouse.builder()
                    .name("Сауна в центре")
                    .description("Современная сауна в сердце города, вместимость до 4 человек")
                    .capacity(4)
                    .pricePerHour(new BigDecimal("25.00"))
                    .location(loc1)
                    .build();
            bathhouseRepo.save(bath2);

            // Баня 3 (привязана к loc2)
            Bathhouse bath3 = Bathhouse.builder()
                    .name("Баня на природе")
                    .description("Традиционная деревянная баня, вместимость до 8 человек")
                    .capacity(8)
                    .pricePerHour(new BigDecimal("35.00"))
                    .location(loc2)
                    .build();
            bathhouseRepo.save(bath3);
        };
    }
}
