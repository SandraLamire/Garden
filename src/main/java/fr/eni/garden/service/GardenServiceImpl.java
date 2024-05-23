package fr.eni.garden.service;


import fr.eni.garden.entity.Garden;

import fr.eni.garden.repository.GardenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GardenServiceImpl implements GardenService {

    private final GardenRepository gardenRepository;

    public GardenServiceImpl(GardenRepository gardenRepository) {
        this.gardenRepository = gardenRepository;
    }

    @Override
    public void addGarden(Garden garden) {
        this.gardenRepository.save(garden);
    }

    @Override
    public void editGarden(Garden garden) {
        this.gardenRepository.save(garden);
    }

    @Override
    public void deleteGarden(Garden garden) {
        this.gardenRepository.delete(garden);
    }

    @Override
    public Optional<Garden> getGarden(Integer idGarden) {
        return this.gardenRepository.findById(idGarden);
    }

    @Override
    public List<Garden> getGardens() {
        return (List<Garden>) this.gardenRepository.findAll();
    }

}
