package poruit.bathbooking.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import poruit.bathbooking.entity.User;
import poruit.bathbooking.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepo;

    public Optional<User> findByTgUsername(String tgUsername) {
        return userRepo.findByTgUsername(tgUsername);
    }

    // Если нужно, можно добавить методы create/update/delete
}
