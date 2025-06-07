package poruit.bathbooking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poruit.bathbooking.entity.Bathhouse;
import poruit.bathbooking.repository.BathhouseRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BathhouseService {

    private final BathhouseRepository bathhouseRepo;

    /** Вернуть список всех бань */
    public List<Bathhouse> findAll() {
        return bathhouseRepo.findAll();
    }

    /** Вернуть баню по её ID (если существует) */
    public Optional<Bathhouse> findById(Long id) {
        return bathhouseRepo.findById(id);
    }

    /**
     * Найти все бани, свободные на интервал [start, end).
     * Возвращает пустой список, если ни одна баня не свободна.
     */
    public List<Bathhouse> findAvailable(LocalDateTime start, LocalDateTime end) {
        return bathhouseRepo.findAvailableBathhouses(start, end);
    }
}
