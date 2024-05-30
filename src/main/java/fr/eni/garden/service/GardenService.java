package fr.eni.garden.service;
import fr.eni.garden.entity.Garden;
import fr.eni.garden.entity.Plant;
import fr.eni.garden.entity.Square;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GardenService {
    void addGarden(Garden garden);

    void editGarden(Garden garden);

    void deleteGarden(Garden garden);

    Optional<Garden> getGarden(Integer idGarden);

    List<Garden> getGardens();

    Map<Garden, List<Square>> getMapSquaresByGarden(boolean emptySquare, Plant plant);

}
