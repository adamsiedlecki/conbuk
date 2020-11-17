package pl.adamsiedlecki.conbuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.adamsiedlecki.conbuk.db.user.UserDs;

@Controller
public class TestController {

    private final UserDs userDs;

    @Autowired
    public TestController(UserDs userDs) {
        this.userDs = userDs;
    }

    @GetMapping("/test")
    private String test() {
        System.out.println("CURRENT USERS DB STATUS: ");
        System.out.println(userDs.findAll());
        return "main";
    }
}
