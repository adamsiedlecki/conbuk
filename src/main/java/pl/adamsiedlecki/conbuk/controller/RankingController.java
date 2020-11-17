package pl.adamsiedlecki.conbuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RankingController {

//    private UserService userService;
//
//    @Autowired
//    public RankingController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/ranking")
    public String getRanking(Model model) {
        return "ranking";
    }
}
