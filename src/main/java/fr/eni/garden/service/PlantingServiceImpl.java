package fr.eni.garden.service;

import fr.eni.garden.entity.Plant;
import fr.eni.garden.entity.Planting;
import fr.eni.garden.entity.Square;
import fr.eni.garden.exception.PlantingException;
import fr.eni.garden.repository.PlantingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlantingServiceImpl implements PlantingService {

    private final PlantingRepository plantingRepository;

    public PlantingServiceImpl(PlantingRepository plantingRepository) {
        this.plantingRepository = plantingRepository;
    }

    @Override
    @Transactional
    public void addPlanting(Planting planting) throws PlantingException {
        if (planting.getSquare().getSquareRemainingSurface() < planting.getPlantingSurface()) {
            throw new PlantingException("There is not enough surface in this square");
        }

        Map<String, Long> plantNameCounting = planting.getSquare()
                .getPlantingList()
                .stream()
                .map(Planting::getPlant)
                .collect(Collectors.groupingBy(Plant::getName, Collectors.counting()));

        if (!(plantNameCounting.get(planting.getPlant().getName()) == null)) {
            if (plantNameCounting.get(planting.getPlant().getName()) >= 3) {
                throw new PlantingException("There is already 3 plants with this name in this square !");
            }
        }

        this.plantingRepository.save(planting);
    }

    @Override
    @Transactional
    public void editPlanting(Planting planting) throws PlantingException {
        Integer registeredPlantingSurface = this.getPlanting(planting.getIdPlanting()).map(Planting::getPlantingSurface).orElse(0);
        if (planting.getSquare().getSquareRemainingSurface() + registeredPlantingSurface < planting.getPlantingSurface()) {
            throw new PlantingException("There is not enough surface in this square");
        }
        this.plantingRepository.save(planting);
    }

    @Override
    @Transactional
    public void deletePlanting(Planting planting) {
        this.plantingRepository.delete(planting);
    }

    @Override
    @Transactional
    public Optional<Planting> getPlanting(Integer idPlanting) {
        return this.plantingRepository.findById(idPlanting);
    }

    @Override
    @Transactional
    public List<Planting> getPlantings() {
        return (List<Planting>) this.plantingRepository.findAll();
    }

    @Override
    @Transactional
    public List<Planting> getPlantingsByPlant(Plant plant) {
        return this.plantingRepository.findAllByPlantIs(plant);
    }

    @Override
    @Transactional
    public List<Planting> getPlantingsBySquare(Square square) {
        return this.plantingRepository.findAllBySquare(square);
    }
}
