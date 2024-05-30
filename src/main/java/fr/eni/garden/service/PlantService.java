package fr.eni.garden.service;

import fr.eni.garden.entity.Plant;
import fr.eni.garden.entity.Planting;
import fr.eni.garden.exception.PlantException;

import java.util.List;
import java.util.Optional;

public interface PlantService {

    Optional<Plant> getPlant(Integer idPlant);

    List<Plant> getPlants();

    void addPlant(Plant plant) throws PlantException;

    void editPlant(Plant plant) throws PlantException;

    void deletePlant(Plant plant);

    List<Planting> getPlantLocationsByName(String plantName);

    List<Planting> getPlantLocationsByNameAndVariety(String plantName, String plantVariety);

}
