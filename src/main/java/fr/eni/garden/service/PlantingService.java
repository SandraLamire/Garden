package fr.eni.garden.service;


import fr.eni.garden.entity.Planting;

import java.util.List;
import java.util.Optional;

public interface PlantingService {
    void addPlanting(Planting planting);

    void editPlanting(Planting planting);

    void deletePlanting(Planting planting);

    Optional<Planting> getPlanting(Integer idPlanting);

    List<Planting> getPlantings();
}
