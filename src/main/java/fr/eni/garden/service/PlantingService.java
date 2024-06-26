package fr.eni.garden.service;

import fr.eni.garden.entity.Plant;
import fr.eni.garden.entity.Planting;
import fr.eni.garden.entity.Square;
import fr.eni.garden.exception.PlantingException;
import java.util.List;
import java.util.Optional;

public interface PlantingService {
    void addPlanting(Planting planting) throws PlantingException;

    void editPlanting(Planting planting) throws PlantingException;

    void deletePlanting(Planting planting);

    Optional<Planting> getPlanting(Integer idPlanting);

    List<Planting> getPlantingsByPlant(Plant plant);

    List<Planting> getPlantingsBySquare(Square square);

    Integer getSquareRemainingSurface(Square square);

    boolean isPlantingHasPlant(Planting planting, Plant plant);

    boolean isAnyPlantingHasPlant(List<Planting> plantings, Plant plant);
}
