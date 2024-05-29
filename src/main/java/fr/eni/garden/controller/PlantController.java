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
    public String showAllPlant(Model model){
        model.addAttribute("plantList", this.plantService.getAll());
        return "plants";
    }

    @GetMapping("/add")
    public String showAddPlant(@ModelAttribute("newPlant")Plant newPlant, Model model){
        model.addAttribute("plantTypes", PlantType.values());
        return "addPlant";
    }

    @PostMapping("/add")
    public String addPlant(@Valid @ModelAttribute("newPlant") Plant newPlant, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("plantTypes", PlantType.values());
            System.out.println(errors);
            return "addPlant";
        }
        try {
            this.plantService.addPlant(newPlant);
        } catch (PlantException plantException) {
            model.addAttribute("plantTypes", PlantType.values());
            errors.addError(new ObjectError("globalError", plantException.getMessage()));
            System.out.println(errors);
            return "addPlant";
        }
        return "redirect:/plant";
    }

    @GetMapping("/{idPlant}/delete")
    public String deletePlant(@PathVariable("idPlant") Integer idPlant) {
        this.plantService.getOne(idPlant).ifPresent(this.plantService::deletePlant);
        //TODO verif using
        return "redirect:/plant";
    }
}


