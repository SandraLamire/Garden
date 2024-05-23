package fr.eni.garden.service;

import fr.eni.garden.entity.Plant;

import java.util.List;
import java.util.Optional;

public interface PlantService {

    Optional<Plant> getOne(Integer idPlant);
    List<Plant> getAll();
    void add(Plant plant);
    void edit(Plant plant);
    void delete(Plant plant);

}
