package fr.eni.garden.controller;

import fr.eni.garden.entity.Plant;
import fr.eni.garden.enums.PlantType;
import fr.eni.garden.exception.PlantException;
import fr.eni.garden.service.PlantService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("plant")
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping
    public String showAllPlant(Model model) {
        model.addAttribute("plantList", this.plantService.getPlants());
        return "plants";
    }

    @GetMapping("/add")
    public String showAddPlant(@ModelAttribute("newPlant") Plant newPlant, Model model) {
        model.addAttribute("plantTypes", PlantType.values());
        return "addPlant";
    }

    @PostMapping("/add")
    public String addPlant(@Valid @ModelAttribute("newPlant") Plant newPlant, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("plantTypes", PlantType.values());
            return "addPlant";
        }
        try {
            this.plantService.addPlant(newPlant);
        } catch (PlantException plantException) {
            model.addAttribute("plantTypes", PlantType.values());
            errors.addError(new ObjectError("globalError", plantException.getMessage()));
            return "addPlant";
        }
        return "redirect:/plant";
    }

    @GetMapping("/{idPlant}/delete")
    public String deletePlant(@PathVariable("idPlant") Integer idPlant) {
        this.plantService.getPlant(idPlant).ifPresent(this.plantService::deletePlant);
        return "redirect:/plant";
    }

    @GetMapping("/{idPlant}/edit")
    public String showEditPlant(@PathVariable("idPlant") Integer idPlant, Model model) {
        this.plantService.getPlant(idPlant).ifPresent(p -> model.addAttribute("currentPlant", p));
        model.addAttribute("plantTypes", PlantType.values());
        return "editPlant";
    }

    @PostMapping("/{idPlant}/edit")
    public String editPlant(@Valid @ModelAttribute("currentPlant") Plant currentPlant, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            return "editPlant";
        }
        try {
            this.plantService.editPlant(currentPlant);
        } catch (PlantException plantException) {
            model.addAttribute("plantTypes", PlantType.values());
            errors.addError(new ObjectError("globalError", plantException.getMessage()));
            return "editPlant";
        }
        return "redirect:/plant";
    }

}


