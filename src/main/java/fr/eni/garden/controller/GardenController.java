package fr.eni.garden.controller;

import fr.eni.garden.entity.Garden;
import fr.eni.garden.service.GardenService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/garden")
public class GardenController {

    private final GardenService gardenService;

    public GardenController(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    @GetMapping
    public String showAllGardens(Model model) {
        model.addAttribute("gardenList", this.gardenService.getGardens());
        return "gardens";
    }

    @GetMapping("/add")
    public String showAddGarden(@ModelAttribute("newGarden") Garden newGarden) {
        return "addGarden";
    }

    @PostMapping("/add")
    public String addGarden(@Valid @ModelAttribute("newGarden") Garden newGarden, BindingResult errors) {
        if (errors.hasErrors()) {
            return "addGarden";
        }
        this.gardenService.addGarden(newGarden);
        return "redirect:/garden";
    }

    @GetMapping("/{idGarden}")
    public String showGarden(@PathVariable("idGarden") Integer idGarden, Model model) {
        Optional<Garden> garden = gardenService.getGarden(idGarden);
        garden.ifPresent(g -> model.addAttribute("garden", g));
        return "garden";
    }

    @GetMapping("/{idGarden}/delete")
    public String deleteGarden(@PathVariable("idGarden") Integer idGarden) {
        this.gardenService.getGarden(idGarden).ifPresent(this.gardenService::deleteGarden);
        return "redirect:/garden";
    }

    @GetMapping("/{idGarden}/edit")
    public String showEditGarden(@PathVariable("idGarden") Integer idGarden, Model model) {
        this.gardenService.getGarden(idGarden).ifPresent( g -> model.addAttribute("currentGarden", g));
        return "editGarden";
    }

    @PostMapping("/{idGarden}/edit")
    public String editGarden(@Valid @ModelAttribute("currentGarden") Garden currentGarden, BindingResult errors) {
        if (errors.hasErrors()) {
            return "editGarden";
        }
        this.gardenService.editGarden(currentGarden);
        return "redirect:/garden";
    }
}
