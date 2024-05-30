package fr.eni.garden.controller;

import fr.eni.garden.entity.Garden;
import fr.eni.garden.entity.Square;
import fr.eni.garden.enums.ExposureType;
import fr.eni.garden.enums.SoilType;
import fr.eni.garden.exception.SquareException;
import fr.eni.garden.service.GardenService;
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
@RequestMapping("/garden/{idGarden}/square")
public class SquareController {

    private final GardenService gardenService;
    private final SquareService squareService;

    public SquareController(GardenService gardenService, SquareService squareService) {
        this.gardenService = gardenService;
        this.squareService = squareService;
    }

    @ModelAttribute
    public void initModel(@PathVariable("idGarden") Integer idGarden, Model model){
        model.addAttribute("soilTypes", SoilType.values());
        model.addAttribute("exposureTypes", ExposureType.values());
        Optional<Garden> garden = this.gardenService.getGarden(idGarden);
        garden.ifPresent( g -> model.addAttribute("garden", g));
    }

    @GetMapping("/add")
    public String showAddSquare(@ModelAttribute("newSquare") Square newSquare) {
        return "addSquare";
    }

    @PostMapping("/add")
    public String addSquare(@PathVariable("idGarden") Integer idGarden, @Valid @ModelAttribute("newSquare") Square newSquare, BindingResult errors, RedirectAttributes redirectAttributes, Model model) {
        if (errors.hasErrors()) {
            return "addSquare";
        }
        try {
            newSquare.setGarden((Garden) model.getAttribute("garden"));
            this.squareService.addSquare(newSquare);
        } catch (SquareException squareException) {
            errors.addError(new ObjectError("globalError", squareException.getMessage()));
            return "addSquare";
        }
        redirectAttributes.addAttribute("idGarden", idGarden);
        return "redirect:/garden/{idGarden}";
    }

    @GetMapping("/{idSquare}/delete")
    public String deleteSquare(@PathVariable("idGarden") Integer idGarden, @PathVariable("idSquare") Integer idSquare, RedirectAttributes redirectAttributes) {
        this.squareService.getOne(idSquare).ifPresent(this.squareService::deleteSquare);
        redirectAttributes.addAttribute("idGarden", idGarden);
        return "redirect:/garden/{idGarden}";
    }

    @GetMapping("/{idSquare}/edit")
    public String showEditSquare(@PathVariable("idSquare") Integer idSquare, Model model) {
        this.squareService.getOne(idSquare).ifPresent(s -> model.addAttribute("currentSquare", s));
        return "editSquare";
    }

    @PostMapping("/{idSquare}/edit")
    public String editSquare(@PathVariable("idGarden") Integer idGarden, @Valid @ModelAttribute("currentSquare") Square currentSquare, BindingResult errors, RedirectAttributes redirectAttributes, Model model) {
        if (errors.hasErrors()) {
            return "editSquare";
        }
        try {
            currentSquare.setGarden((Garden) model.getAttribute("garden"));
            this.squareService.editSquare(currentSquare);
        } catch (SquareException squareException) {
            errors.addError(new ObjectError("globalError", squareException.getMessage()));
            return "editSquare";
        }
        redirectAttributes.addAttribute("idGarden", idGarden);
        return "redirect:/garden/{idGarden}";
    }
}
