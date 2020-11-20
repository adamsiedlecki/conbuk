package pl.adamsiedlecki.conbuk.controller.secured;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.adamsiedlecki.conbuk.db.concept.Concept;
import pl.adamsiedlecki.conbuk.db.concept.ConceptService;
import pl.adamsiedlecki.conbuk.db.user.User;
import pl.adamsiedlecki.conbuk.db.user.UserDs;
import pl.adamsiedlecki.conbuk.tools.GetCurrentLoggedUser;

import java.util.Optional;

@Controller
public class DislikeController {

    private static final Logger log = LoggerFactory.getLogger(DislikeController.class);
    private final ConceptService conceptService;
    private final UserDs userDs;

    @Autowired
    public DislikeController(ConceptService conceptService, UserDs userDs) {
        this.userDs = userDs;
        this.conceptService = conceptService;
    }

    @GetMapping("/add-dislike")
    @ResponseBody
    public String addLike(@RequestParam String conceptName, @RequestParam String username) {
        Optional<User> userByUsername = userDs.getUserByUsername(username);
        if (userByUsername.isPresent()) {
            if (!userByUsername.get().getUsername().equals(GetCurrentLoggedUser.get().get().getUsername())) {
                log.error("LOGGED USER AND USER GOT IN REQUEST ARE NOT THE SAME");
                System.out.println("req: " + userByUsername.get() + " | logged: " + GetCurrentLoggedUser.get().get());
                return "security error";
            }
            Optional<Concept> conceptByName = conceptService.getConceptByName(conceptName);
            if (conceptByName.isPresent()) {
                if (conceptByName.get().getDislikeUsers().contains(userByUsername.get())) {
                    // undo dislike
                    conceptByName.get().getDislikeUsers().remove(userByUsername.get());
                    conceptService.flush();
                    return "" + conceptByName.get().getDislikeUsers().size();
                } else {
                    // dislike
                    conceptByName.get().getDislikeUsers().add(userByUsername.get());
                    conceptByName.get().getLikeUsers().remove(userByUsername.get());
                    conceptService.flush();
                    return "" + conceptByName.get().getDislikeUsers().size() + " \uD83D\uDC4E";
                }

            } else {
                log.error("concept with name: " + conceptName + " does not exist");
            }

        } else {
            log.error("user with name: " + conceptName + " does not exist");
        }
        return "";
    }

    @GetMapping("/dislikes")
    @ResponseBody
    public String getDislikes(@RequestParam String conceptName) {
        Optional<Concept> conceptByName = conceptService.getConceptByName(conceptName);
        if (conceptByName.isPresent()) {
            return "" + conceptByName.get().getDislikeUsers().size();
        } else {
            return "no such concept in db";
        }
    }

}
