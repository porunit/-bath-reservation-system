package poruit.bathbooking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poruit.bathbooking.entity.Bathhouse;
import poruit.bathbooking.entity.Reservation;
import poruit.bathbooking.entity.User;
import poruit.bathbooking.repository.BathhouseRepository;
import poruit.bathbooking.repository.ReservationRepository;
import poruit.bathbooking.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepo;
    private final BathhouseRepository bathhouseRepo;
    private final UserRepository userRepo;

    public List<Reservation> findAll() {
        return reservationRepo.findAll();
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepo.findById(id);
    }

    /**
     * Создание бронирования:
     *   1) Проверяем, что пользователь с tgUsername существует.
     *   2) Проверяем, что баня с bathhouseId существует.
     *   3) Проверяем, нет ли пересечений бронирований.
     *   4) Сохраняем новую запись.
     */
    public Reservation createReservation(String tgUsername, Long bathhouseId,
                                         LocalDateTime start, LocalDateTime end) {
        User user = userRepo.findByTgUsername(tgUsername)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Пользователь с tgUsername=" + tgUsername + " не найден"));

        Bathhouse bathhouse = bathhouseRepo.findById(bathhouseId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Баня с id=" + bathhouseId + " не найдена"));

        List<Reservation> overlaps = reservationRepo
                .findByBathhouseIdAndStartDateTimeLessThanAndEndDateTimeGreaterThan(
                        bathhouseId, end, start);
        if (!overlaps.isEmpty()) {
            throw new IllegalStateException("На выбранный интервал баня уже забронирована");
        }

        Reservation reservation = Reservation.builder()
                .user(user)
                .bathhouse(bathhouse)
                .startDateTime(start)
                .endDateTime(end)
                .build();

        return reservationRepo.save(reservation);
    }

    public void deleteById(Long id) {
        reservationRepo.deleteById(id);
    }
}
