package leviathan.dev.coronavirustracker.controllers;

import leviathan.dev.coronavirustracker.models.LocationStats;
import leviathan.dev.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = CoronaVirusDataService.getAllStats();
        int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDeffDiferenceDay()).sum();
        model.addAttribute("totalNewCases", totalNewCases);
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalCases);

        return "home";
    }
}
