package fr.eni.garden.service;

import fr.eni.garden.entity.Plant;
import fr.eni.garden.exception.PlantException;
import fr.eni.garden.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;

    public PlantServiceImpl(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public Optional<Plant> getOne(Integer idPlant) {
        return this.plantRepository.findById(idPlant);
    }

    @Override
    public List<Plant> getAll() {
        return (List<Plant>) this.plantRepository.findAll();
    }

    @Override
    public void addPlant(Plant plant) throws PlantException {
        List<Plant> similarPlants =  this.getAll().stream().filter(p -> plant.getNameAndVariety().equals(p.getNameAndVariety())).toList();
        if (!similarPlants.isEmpty()) {
            throw new PlantException("This plant already exist !");
        }
        this.plantRepository.save(plant);
    }

    @Override
    public void editPlant(Plant plant) throws PlantException {
        List<Plant> similarPlants =  this.getAll().stream().filter(p -> plant.getNameAndVariety().equals(p.getNameAndVariety())).toList();
        if (!similarPlants.isEmpty()) {
            throw new PlantException("This plant already exist !");
        }
        this.plantRepository.save(plant);
    }

    @Override
    public void deletePlant(Plant plant) {
        this.plantRepository.delete(plant);
    }
}
