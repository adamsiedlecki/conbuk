package pl.adamsiedlecki.conbuk.controller.secured;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.adamsiedlecki.conbuk.tools.GetCurrentLinesOfCodeOnGithubRepo;

@Controller
public class StatisticsController {

    @GetMapping("/statistics")
    public String getStats(Model m) {
        m.addAttribute("stats", GetCurrentLinesOfCodeOnGithubRepo.get());
        return "statistics";
    }
}
