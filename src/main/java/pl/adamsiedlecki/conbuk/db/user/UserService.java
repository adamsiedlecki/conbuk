package pl.adamsiedlecki.conbuk.db.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> getUserByUsername(String username) {
        return Optional.of(userRepo.findByUsername(username));
    }

    public boolean saveUser(User user) {
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            return false;
        }
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            return false;
        }
        userRepo.saveAndFlush(user);
        return true;
    }
}
