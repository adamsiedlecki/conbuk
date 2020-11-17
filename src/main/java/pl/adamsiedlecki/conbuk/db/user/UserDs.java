package pl.adamsiedlecki.conbuk.db.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDs implements UserDetailsService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserDs(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;

        User user = new User();
        user.setUsername("a");
        user.setPassword(passwordEncoder.encode("a"));
        userRepo.saveAndFlush(user);

        User user2 = new User();
        user2.setUsername("admin");
        user2.setPassword(passwordEncoder.encode("admin"));
        userRepo.saveAndFlush(user2);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
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

    public List<User> findAll() {
        return userRepo.findAll();
    }
}
