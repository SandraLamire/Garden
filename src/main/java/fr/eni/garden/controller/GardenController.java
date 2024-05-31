package fr.eni.garden.controller;

import fr.eni.garden.entity.Garden;
import fr.eni.garden.entity.Plant;
import fr.eni.garden.service.GardenService;
import fr.eni.garden.service.PlantService;
import fr.eni.garden.service.SquareService;
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
    private final SquareService squareService;
    private final PlantService plantService;

    public GardenController(GardenService gardenService, SquareService squareService, PlantService plantService) {
        this.gardenService = gardenService;
        this.squareService = squareService;
        this.plantService = plantService;
    }

    @GetMapping
    public String showAllGardens(@RequestParam(name = "plant", required = false) Plant plant, @RequestParam(name = "emptySquare", required = false) boolean emptySquare , Model model) {
        model.addAttribute("gardenList", this.gardenService.getGardensFilter(emptySquare, plant));
        model.addAttribute("mapSquaresByGarden", this.gardenService.getMapSquaresByGarden(emptySquare, plant));
        model.addAttribute("mapPlantingsBySquare", this.squareService.getMapPlantingsBySquare(emptySquare, plant));
        model.addAttribute("plantList", this.plantService.getPlants());
        return "gardens";
    }

    @GetMapping("/add")
    public String showAddGarden(@ModelAttribute("newGarden") Garden newGarden) {
        return "addGarden";
    }

    @PostMapping("/add")
    public String addGarden(@Valid @ModelAttribute("newGarden") Garden newGarden, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            return "addGarden";
        }
        this.gardenService.addGarden(newGarden);
        model.addAttribute("gardenRemainingSurface", this.squareService.getGardenRemainingSurface((Garden) model.getAttribute("garden")));
        model.addAttribute("squareRemainingSurface", this.squareService.getSquareRemainingSurfaceBySquare((Garden) model.getAttribute("garden")));
        return "redirect:/garden";
    }

    @GetMapping("/{idGarden}")
    public String showGarden(@PathVariable("idGarden") Integer idGarden, Model model) {
        Optional<Garden> garden = gardenService.getGarden(idGarden);
        garden.ifPresent(g -> model.addAttribute("garden", g));
        garden.ifPresent(g -> model.addAttribute("squareList", this.squareService.getSquaresByGarden(g)));
        garden.ifPresent(g -> model.addAttribute("plantingListMap", this.squareService.getPlantingsBySquare(g)));
        model.addAttribute("gardenRemainingSurface", this.squareService.getGardenRemainingSurface((Garden) model.getAttribute("garden")));
        model.addAttribute("squareRemainingSurface", this.squareService.getSquareRemainingSurfaceBySquare((Garden) model.getAttribute("garden")));
        return "garden";
    }

    @GetMapping("/{idGarden}/delete")
    public String deleteGarden(@PathVariable("idGarden") Integer idGarden, Model model) {
        this.gardenService.getGarden(idGarden).ifPresent(this.gardenService::deleteGarden);
        model.addAttribute("gardenRemainingSurface", this.squareService.getGardenRemainingSurface((Garden) model.getAttribute("garden")));
        model.addAttribute("squareRemainingSurface", this.squareService.getSquareRemainingSurfaceBySquare((Garden) model.getAttribute("garden")));
        return "redirect:/garden";
    }

    @GetMapping("/{idGarden}/edit")
    public String showEditGarden(@PathVariable("idGarden") Integer idGarden, Model model) {
        this.gardenService.getGarden(idGarden).ifPresent(g -> model.addAttribute("currentGarden", g));
        return "editGarden";
    }

    @PostMapping("/{idGarden}/edit")
    public String editGarden(@Valid @ModelAttribute("currentGarden") Garden currentGarden, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            return "editGarden";
        }
        model.addAttribute("gardenRemainingSurface", this.squareService.getGardenRemainingSurface((Garden) model.getAttribute("garden")));
        model.addAttribute("squareRemainingSurface", this.squareService.getSquareRemainingSurfaceBySquare((Garden) model.getAttribute("garden")));
        this.gardenService.editGarden(currentGarden);
        return "redirect:/garden";
    }
}
