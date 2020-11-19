package pl.adamsiedlecki.conbuk.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.adamsiedlecki.conbuk.db.user.MyUserPrincipal;
import pl.adamsiedlecki.conbuk.db.user.User;

import java.util.Optional;

public class GetCurrentLoggedUser {

    private static final Logger log = LoggerFactory.getLogger(GetCurrentLoggedUser.class);

    public static Optional<User> get() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return Optional.of(((MyUserPrincipal) principal).getUser());
        }
        log.error("CANNOT GET CURRENT LOGGED USER!");
        return Optional.empty();
    }
}
