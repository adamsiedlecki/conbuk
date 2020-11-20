package pl.adamsiedlecki.conbuk.controller.notSecured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.adamsiedlecki.conbuk.db.user.User;
import pl.adamsiedlecki.conbuk.db.user.UserDs;
import pl.adamsiedlecki.conbuk.pojo.UserRegister;

@Controller
public class RegisterController {

    private final UserDs userDs;

    @Autowired
    public RegisterController(UserDs userDs) {
        this.userDs = userDs;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegister", new UserRegister());
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute UserRegister userRegister) {

        if (userDs.getUserByUsername(userRegister.getUsername()).isPresent()) {
            model.addAttribute("registerUsernameExist", true);
            return "register";
        }
        if (!userRegister.getPassword().equals(userRegister.getPassword2())) {
            model.addAttribute("registerPasswordsDontMatch", true);
            return "register";
        }
        User user = new User();
        user.setUsername(userRegister.getUsername());
        user.setPassword(userRegister.getPassword());
        userDs.saveUser(user);
        model.addAttribute("registerSuccessful", true);
        return "login";

    }
}
