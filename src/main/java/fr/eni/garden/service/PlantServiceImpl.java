package fr.eni.garden.service;

import fr.eni.garden.entity.Plant;
import fr.eni.garden.entity.Planting;
import fr.eni.garden.exception.PlantException;
import fr.eni.garden.repository.PlantRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlantServiceImpl implements PlantService {

    private final PlantRepository plantRepository;
    private final PlantingService plantingService;

    public PlantServiceImpl(PlantRepository plantRepository, PlantingService plantingService) {
        this.plantRepository = plantRepository;
        this.plantingService = plantingService;
    }

    @Override
    @Transactional
    public Optional<Plant> getOne(Integer idPlant) {
        return this.plantRepository.findById(idPlant);
    }

    @Override
    @Transactional
    public List<Plant> getAll() {
        return (List<Plant>) this.plantRepository.findAll();
    }

    @Override
    @Transactional
    public void addPlant(Plant plant) throws PlantException {
        List<Plant> similarPlants =  this.getAll().stream().filter(p -> plant.getNameAndVariety().equals(p.getNameAndVariety())).toList();
        if (!similarPlants.isEmpty()) {
            throw new PlantException("This plant already exist !");
        }
        this.plantRepository.save(plant);
    }

    @Override
    @Transactional
    public void editPlant(Plant plant) throws PlantException {
        List<Plant> similarPlants =  this.getAll().stream().filter(p -> plant.getNameAndVariety().equals(p.getNameAndVariety())).toList();
        if (!similarPlants.isEmpty()) {
            throw new PlantException("This plant already exist !");
        }
        this.plantRepository.save(plant);
    }

    @Override
    @Transactional
    public void deletePlant(Plant plant) {
        this.plantRepository.delete(plant);
    }

    @Override
    @Transactional
    public List<Planting> getPlantLocationsByName(String plantName) {
        List<Plant> plants = this.plantRepository.findAllByName(plantName);
        return plants.stream().map(this.plantingService::getPlantingsByPlant).flatMap(List::stream)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Planting> getPlantLocationsByNameAndVariety(String plantName, String plantVariety) {
        Plant plant = this.plantRepository.findByNameAndVariety(plantName, plantVariety);
        return this.plantingService.getPlantingsByPlant(plant);
    }
}