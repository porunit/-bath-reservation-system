package poruit.bathbooking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poruit.bathbooking.dto.ReservationRequest;
import poruit.bathbooking.entity.Reservation;
import poruit.bathbooking.service.ReservationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * GET /reservations
     *  - Без параметров: возвращает все бронирования.
     *  - Если указан bathhouseId – возвращает все бронирования для этой бани.
     *  - Если указан tgUsername – возвращает все бронирования этого пользователя.
     *
     * Пример:
     *   GET /reservations?bathhouseId=2
     *   GET /reservations?tgUsername=ivan_telegram
     */
    @GetMapping
    public ResponseEntity<List<Reservation>> getAll(
            @RequestParam(value = "bathhouseId", required = false) Long bathhouseId,
            @RequestParam(value = "tgUsername",  required = false) String tgUsername
    ) {
        List<Reservation> all = reservationService.findAll();

        // Фильтруем по bathhouseId, если задан
        if (bathhouseId != null) {
            all = all.stream()
                    .filter(r -> r.getBathhouse().getId().equals(bathhouseId))
                    .collect(Collectors.toList());
        }

        // Фильтруем по tgUsername, если задан
//        if (tgUsername != null && !tgUsername.isBlank()) {
//            all = all.stream()
//                    .filter(r -> r.getUser().getTgUsername().equals(tgUsername))
//                    .collect(Collectors.toList());
//        }

        return ResponseEntity.ok(all);
    }

    /**
     * GET /reservations/{id}
     * Вернуть бронирование по ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable Long id) {
        Optional<Reservation> opt = reservationService.findById(id);
        return opt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST /reservations
     * Создать новое бронирование. Тело JSON:
     * {
     *   "tgUsername": "telegram_user",
     *   "bathhouseId": 1,
     *   "startDateTime": "2025-06-15T14:00:00",
     *   "endDateTime":   "2025-06-15T18:00:00"
     * }
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ReservationRequest request) {
        try {
            Reservation saved = reservationService.createReservation(
                    request.getTgUsername(),
                    request.getBathhouseId(),
                    request.getStartDateTime(),
                    request.getEndDateTime()
            );
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException | IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /**
     * DELETE /reservations/{id}
     * Удалить бронирование по ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
