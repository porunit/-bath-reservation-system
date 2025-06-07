package poruit.bathbooking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poruit.bathbooking.entity.Bathhouse;
import poruit.bathbooking.service.BathhouseService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bathhouses")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BathhouseController {

    private final BathhouseService bathhouseService;

    /**
     * GET /bathhouses
     * Возвращает список всех бань.
     */
    @GetMapping
    public ResponseEntity<List<Bathhouse>> getAll() {
        List<Bathhouse> all = bathhouseService.findAll();
        return ResponseEntity.ok(all);
    }

    /**
     * GET /bathhouses/{id}
     * Возвращает баню по её идентификатору, либо 404, если не найдена.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Bathhouse> getById(@PathVariable Long id) {
        Optional<Bathhouse> opt = bathhouseService.findById(id);
        return opt.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /bathhouses/available?start={startDateTime}&end={endDateTime}
     * Возвращает список бань, свободных на интервал [start, end).
     * Параметры передаются в формате ISO-8601, например: 2025-06-15T14:00:00
     */
    @GetMapping("/available")
    public ResponseEntity<List<Bathhouse>> getAvailable(
            @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime start,

            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime end
    ) {
        // Простая валидация: start < end
        if (start == null || end == null || !start.isBefore(end)) {
            return ResponseEntity.badRequest().build();
        }

        List<Bathhouse> available = bathhouseService.findAvailable(start, end);
        return ResponseEntity.ok(available);
    }
}
