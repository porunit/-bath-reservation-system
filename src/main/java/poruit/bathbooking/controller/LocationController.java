package poruit.bathbooking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poruit.bathbooking.entity.Location;
import poruit.bathbooking.service.LocationService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LocationController {

    private final LocationService locationService;

    /**
     * GET /locations
     * Возвращает список всех локаций.
     */
    @GetMapping
    public ResponseEntity<List<Location>> getAll() {
        List<Location> all = locationService.findAll();
        return ResponseEntity.ok(all);
    }

    /**
     * GET /locations/{id}
     * Возвращает локацию по ID либо 404.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Location> getById(@PathVariable Long id) {
        Optional<Location> opt = locationService.findById(id);
        return opt.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
