package fr.eni.garden.service;

import fr.eni.garden.entity.Plant;
import fr.eni.garden.exception.PlantException;

import java.util.List;
import java.util.Optional;

public interface PlantService {

    Optional<Plant> getOne(Integer idPlant);

    List<Plant> getAll();

    void addPlant(Plant plant) throws PlantException;

    void editPlant(Plant plant) throws PlantException;

    void deletePlant(Plant plant);

}
