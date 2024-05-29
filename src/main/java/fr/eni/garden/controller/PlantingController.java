package fr.eni.garden.controller;


import fr.eni.garden.entity.Garden;
import fr.eni.garden.entity.Planting;
import fr.eni.garden.entity.Square;
import fr.eni.garden.exception.PlantingException;
import fr.eni.garden.service.GardenService;
import fr.eni.garden.service.PlantService;
import fr.eni.garden.service.PlantingService;
import fr.eni.garden.service.SquareService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/garden/{idGarden}/square/{idSquare}/planting")
public class PlantingController {

    private final GardenService gardenService;
    private final SquareService squareService;
    private final PlantService plantService;
    private final PlantingService plantingService;

    public PlantingController(GardenService gardenService, SquareService squareService, PlantService plantService, PlantingService plantingService) {
        this.gardenService = gardenService;
        this.squareService = squareService;
        this.plantService = plantService;
        this.plantingService = plantingService;
    }

    @ModelAttribute
    public void initModel(@PathVariable("idGarden") Integer idGarden, @PathVariable("idSquare") Integer idSquare, Model model) {
        Optional<Garden> garden = this.gardenService.getGarden(idGarden);
        garden.ifPresent(g -> model.addAttribute("garden", g));
        Optional<Square> square = this.squareService.getOne(idSquare);
        square.ifPresent(s -> model.addAttribute("square", s));
        model.addAttribute("plantList", this.plantService.getAll());
    }

    @GetMapping("/add")
    public String showAddPlanting(@ModelAttribute("newPlanting") Planting newPlanting) {
        return "addPlanting";
    }

    @PostMapping("/add")
    public String addPlanting(@PathVariable("idGarden") Integer idGarden, @Valid @ModelAttribute("newPlanting") Planting newPlanting, BindingResult errors, RedirectAttributes redirectAttributes, Model model) {
        if (errors.hasErrors()) {
            return "addPlanting";
        }
        try {
            newPlanting.setSquare((Square) model.getAttribute("square"));
            this.plantingService.addPlanting(newPlanting);
        } catch (PlantingException plantingException) {
            errors.addError(new ObjectError("globalError", plantingException.getMessage()));
            return "addPlanting";
        }
        redirectAttributes.addAttribute("idGarden", idGarden);
        return "redirect:/garden/{idGarden}";
    }

    @GetMapping("/{idPlanting}/delete")
    public String deletePlanting(@PathVariable("idGarden") Integer idGarden, @PathVariable("idPlanting") Integer idPlanting, RedirectAttributes redirectAttributes) {
        this.plantingService.getPlanting(idPlanting).ifPresent(this.plantingService::deletePlanting);
        redirectAttributes.addAttribute("idGarden", idGarden);
        return "redirect:/garden/{idGarden}";
    }
}
