package fr.eni.garden.service;
import fr.eni.garden.entity.Garden;
import fr.eni.garden.entity.Plant;
import fr.eni.garden.entity.Square;
import fr.eni.garden.repository.GardenRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GardenServiceImpl implements GardenService {

    private final GardenRepository gardenRepository;
    private final SquareService squareService;
    private final PlantingService plantingService;

    public GardenServiceImpl(GardenRepository gardenRepository, SquareService squareService, PlantingService plantingService) {
        this.gardenRepository = gardenRepository;
        this.squareService = squareService;
        this.plantingService = plantingService;
    }

    @Override
    @Transactional
    public void addGarden(Garden garden) {
        this.gardenRepository.save(garden);
    }

    @Override
    @Transactional
    public void editGarden(Garden garden) {
        this.gardenRepository.save(garden);
    }

    @Override
    @Transactional
    public void deleteGarden(Garden garden) {
        this.squareService.getSquaresByGarden(garden).forEach(this.squareService::deleteSquare);
        this.gardenRepository.delete(garden);
    }

    @Override
    @Transactional
    public Optional<Garden> getGarden(Integer idGarden) {
        return this.gardenRepository.findById(idGarden);
    }

    @Override
    @Transactional
    public List<Garden> getGardens() {
        return (List<Garden>) this.gardenRepository.findAll();
    }

    @Override
    @Transactional
    public Map<Garden, List<Square>> getMapSquaresByGarden(boolean emptySquare, Plant plant) {
        return this.getGardens().stream().collect(Collectors.toMap(
                g -> g,
                g -> this.squareService.getSquaresByGarden(g).stream()
                        .filter(square -> (!emptySquare || this.plantingService.getPlantingsBySquare(square).isEmpty()) && plant == null || this.plantingService.getPlantingsBySquare(square).stream().anyMatch(planting -> planting.getPlant().equals(plant)))
                        .collect(Collectors.toList())
        ));
    }

}
