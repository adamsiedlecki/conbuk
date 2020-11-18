package pl.adamsiedlecki.conbuk.controller.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.adamsiedlecki.conbuk.db.concept.Concept;
import pl.adamsiedlecki.conbuk.db.concept.ConceptService;

import java.util.List;

@Controller
public class NewestConceptsController {

    private final ConceptService conceptService;

    @Autowired
    public NewestConceptsController(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    @GetMapping("/newest-concepts")
    public String getRanking(Model model) {
        List<Concept> concepts = conceptService.getNewest();
        model.addAttribute("concepts", concepts);
        return "newestConcepts";
    }
}
