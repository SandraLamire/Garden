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

    public GardenServiceImpl(GardenRepository gardenRepository, SquareService squareService) {
        this.gardenRepository = gardenRepository;
        this.squareService = squareService;
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
    public List<Garden> getGardensFilter(boolean emptySquare, Plant plant) {
        return ((List<Garden>) this.gardenRepository.findAll()).stream().filter( g -> {
            List<Square> squares = this.squareService.getSquaresByGarden(g);
            if (emptySquare && plant == null) {
                return this.squareService.isAnySquareEmpty(squares);
            } else if (!emptySquare && plant != null) {
                return this.squareService.isAnySquareHasPlantingWithPlant(squares,plant);
            } else if (emptySquare){
                return this.squareService.isAnySquareEmpty(squares) || this.squareService.isAnySquareHasPlantingWithPlant(squares, plant);
            } else {
                return true;
            }
        }).toList();
    }

    @Override
    @Transactional
    public Map<Garden,List<Square>> getMapSquaresByGarden(boolean emptySquare, Plant plant) {
        return this.getGardens().stream().collect(Collectors.toMap(
            g -> g,
            g -> this.squareService.getSquaresByGarden(g).stream()
                .filter(s -> {
                    if (emptySquare && plant == null) {
                        return this.squareService.isSquareEmpty(s);
                    } else if (!emptySquare && plant != null) {
                        return this.squareService.isSquareHasPlantingWithPlant(s,plant);
                    } else if (emptySquare) {
                        return this.squareService.isSquareEmpty(s) || this.squareService.isSquareHasPlantingWithPlant(s,plant);
                    } else {
                        return true;
                    }
                }).collect(Collectors.toList())
        ));
    }
}
