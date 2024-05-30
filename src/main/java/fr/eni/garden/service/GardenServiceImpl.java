package fr.eni.garden.service;

import fr.eni.garden.entity.Garden;
import fr.eni.garden.repository.GardenRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GardenServiceImpl implements GardenService {

    private final GardenRepository gardenRepository;
    private final SquareService squareService;

    public GardenServiceImpl(GardenRepository gardenRepository, SquareService squareService) {
        this.gardenRepository = gardenRepository;
        this.squareService = squareService;
    }

    @Override
    @Transactional
    public void addGarden(Garden garden) {
        this.gardenRepository.save(garden);
    }

    @Override
    @Transactional
    public void editGarden(Garden garden) {
        this.gardenRepository.save(garden);
    }

    @Override
    @Transactional
    public void deleteGarden(Garden garden) {
        garden.getSquareList().forEach(this.squareService::deleteSquare);
        this.gardenRepository.delete(garden);
    }

    @Override
    @Transactional
    public Optional<Garden> getGarden(Integer idGarden) {
        return this.gardenRepository.findById(idGarden);
    }

    @Override
    @Transactional
    public List<Garden> getGardens() {
        return (List<Garden>) this.gardenRepository.findAll();
    }

}
