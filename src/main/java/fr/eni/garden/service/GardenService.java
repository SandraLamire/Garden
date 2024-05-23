package fr.eni.garden.service;


import fr.eni.garden.entity.Garden;

import java.util.List;
import java.util.Optional;

public interface GardenService {
    void addGarden(Garden garden);
    void updateGarden(Garden garden);
    void deleteGarden(Garden garden);
    Optional<Garden> getGarden(Integer idGarden);
    List<Garden> getGardens();
}
