package fr.eni.garden.controller;

import fr.eni.garden.entity.Garden;
import fr.eni.garden.service.GardenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/garden")
public class GardenController
{
    private final GardenService gardenService;

    public GardenController(GardenService gardenService) {
        this.gardenService = gardenService;
    }

    @GetMapping
    public List<Garden> getGardens() {
        return this.gardenService.getGardens();
    }

    @GetMapping("/{idGarden}")
    public Optional<Garden> getGarden(@PathVariable Integer idGarden) {
        return this.gardenService.getGarden(idGarden);
    }

    @PostMapping
    public void addGarden(@RequestBody Garden garden) {
        this.gardenService.addGarden(garden);
    }

    @PutMapping
    public void editGarden(@RequestBody Garden garden) {
        this.gardenService.editGarden(garden);
    }

    @DeleteMapping
    public void deleteGarden(@RequestBody Garden garden) {
        this.gardenService.deleteGarden(garden);
    }
}
