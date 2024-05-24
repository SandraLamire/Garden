package fr.eni.garden.service;


import fr.eni.garden.entity.Planting;
import fr.eni.garden.exception.PlantingException;
import fr.eni.garden.repository.PlantingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlantingServiceImpl implements PlantingService {

    private final PlantingRepository plantingRepository;
    private final SquareService squareService;

    public PlantingServiceImpl(PlantingRepository plantingRepository, SquareService squareService) {
        this.plantingRepository = plantingRepository;
        this.squareService = squareService;
    }

    @Override
    public void addPlanting(Planting planting) throws PlantingException {
        if (planting.getSquare().getSquareRemainingSurface() < planting.getPlantingSurface()) {
            throw new PlantingException("There is not enough surface in this square");
        }

        Map<String,Long> plantNameCounting = this.squareService.getPlantNameCounting(planting.getSquare());

        if (plantNameCounting.get(planting.getPlant().getName()) >= 3){
            throw new PlantingException("There is already 3 plants with this name in this square !");
        }

        this.plantingRepository.save(planting);
    }

    @Override
    public void editPlanting(Planting planting) throws PlantingException {
        if (planting.getSquare().getSquareRemainingSurface() < planting.getPlantingSurface()) {
            throw new PlantingException("There is not enough surface in this square");
        }
        this.plantingRepository.save(planting);
    }

    @Override
    public void deletePlanting(Planting planting) {
        this.plantingRepository.delete(planting);
    }

    @Override
    public Optional<Planting> getPlanting(Integer idPlanting) {
        return this.plantingRepository.findById(idPlanting);
    }

    @Override
    public List<Planting> getPlantings() {
        return (List<Planting>) this.plantingRepository.findAll();
    }
}
