package pl.adamsiedlecki.conbuk.controller.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.adamsiedlecki.conbuk.db.concept.Concept;
import pl.adamsiedlecki.conbuk.db.concept.ConceptService;
import pl.adamsiedlecki.conbuk.tools.GetCurrentLoggedUser;

import java.util.List;

@Controller
public class RankingController {

    private final ConceptService conceptService;

    @Autowired
    public RankingController(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    @GetMapping("/ranking")
    public String getRanking(Model model) {
        List<Concept> concepts = conceptService.getTop100();
        model.addAttribute("concepts", concepts);
        model.addAttribute("loggedUser", GetCurrentLoggedUser.get());
        return "ranking";
    }
}
