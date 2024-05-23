package fr.eni.garden.service;


import fr.eni.garden.entity.Planting;
import fr.eni.garden.repository.PlantingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantingServiceImpl implements PlantingService {

    private final PlantingRepository plantingRepository;

    public PlantingServiceImpl(PlantingRepository plantingRepository) {
        this.plantingRepository = plantingRepository;
    }

    @Override
    public void addPlanting(Planting planting) {
        this.plantingRepository.save(planting);
    }

    @Override
    public void editPlanting(Planting planting) {
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
