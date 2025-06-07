package poruit.bathbooking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poruit.bathbooking.entity.Location;
import poruit.bathbooking.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepo;

    public List<Location> findAll() {
        return locationRepo.findAll();
    }

    public Optional<Location> findById(Long id) {
        return locationRepo.findById(id);
    }
}
