package pl.adamsiedlecki.conbuk.controller.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.adamsiedlecki.conbuk.db.concept.Concept;
import pl.adamsiedlecki.conbuk.db.concept.ConceptService;
import pl.adamsiedlecki.conbuk.db.user.User;
import pl.adamsiedlecki.conbuk.db.user.UserDs;
import pl.adamsiedlecki.conbuk.pojo.UserId;

import java.util.List;

@Controller
public class AdminPanel {

    private final UserDs userDs;
    private final ConceptService conceptService;

    @Autowired
    public AdminPanel(UserDs userDs, ConceptService conceptService) {
        this.userDs = userDs;
        this.conceptService = conceptService;
    }

    @GetMapping("/admin-panel")
    public String getAdminPanel(Model m) {
        m.addAttribute("users", userDs.findAll());
        m.addAttribute("userId", new UserId());
        return "adminPanel";
    }

    @PostMapping("/remove-user")
    public String removeUser(@ModelAttribute UserId userId, Model m) {
        boolean existed = false;
        if (userDs.getUserById(userId.getId()).isPresent()) {
            existed = true;
            List<Concept> all = conceptService.findAll();
            User u = userDs.getUserById(userId.getId()).get();
            for (Concept c : all) {
                c.getLikeUsers().remove(u);
                c.getDislikeUsers().remove(u);
            }
            userDs.removeUserById(userId.getId());
            m.addAttribute("deleted", existed);
        }


        return "adminPanel";
    }
}
