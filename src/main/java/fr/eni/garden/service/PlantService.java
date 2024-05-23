package fr.eni.garden.service;

import fr.eni.garden.entity.Plant;

import java.util.List;
import java.util.Optional;

public interface PlantService {

    Optional<Plant> getOne(Integer idPlant);

    List<Plant> getAll();

    void addPlant(Plant plant);

    void editPlant(Plant plant);

    void deletePlant(Plant plant);

}
