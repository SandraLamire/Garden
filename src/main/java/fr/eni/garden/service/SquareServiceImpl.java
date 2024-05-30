package fr.eni.garden.service;

import fr.eni.garden.entity.Garden;
import fr.eni.garden.entity.Plant;
import fr.eni.garden.entity.Planting;
import fr.eni.garden.entity.Square;
import fr.eni.garden.exception.SquareException;
import fr.eni.garden.repository.SquareRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SquareServiceImpl implements SquareService {

    private final SquareRepository squareRepository;
    private final PlantingService plantingService;

    public SquareServiceImpl(SquareRepository squareRepository, PlantingService plantingService) {
        this.squareRepository = squareRepository;
        this.plantingService = plantingService;
    }

    @Override
    @Transactional
    public Optional<Square> getSquare(Integer idSquare) {
        return this.squareRepository.findById(idSquare);
    }

    @Override
    @Transactional
    public List<Square> getSquares() {
        return (List<Square>) this.squareRepository.findAll();
    }

    @Override
    @Transactional
    public void addSquare(Square square) throws SquareException {
        if (this.getGardenRemainingSurface(square.getGarden()) < square.getSquareSurface()) {
            throw new SquareException("There is not enough surface in this garden");
        }
        this.squareRepository.save(square);
    }

    @Override
    @Transactional
    public void editSquare(Square square) throws SquareException {
        Integer registeredSquareSurface = this.getSquare(square.getIdSquare()).map(Square::getSquareSurface).orElse(0);
        if (this.getGardenRemainingSurface(square.getGarden()) + registeredSquareSurface < square.getSquareSurface()) {
            throw new SquareException("There is not enough surface in this garden");
        }
        this.squareRepository.save(square);
    }

    @Override
    @Transactional
    public void deleteSquare(Square square) {
        this.plantingService.getPlantingsBySquare(square).forEach(this.plantingService::deletePlanting);
        this.squareRepository.delete(square);
    }

    @Override
    @Transactional
    public List<Square> getSquaresByGarden(Garden garden) {
        return this.squareRepository.findAllByGarden(garden);
    }

    @Override
    @Transactional
    public Map<Square, List<Planting>> getPlantingsBySquare(Garden garden) {
        return this.getSquaresByGarden(garden).stream().collect(Collectors.toMap(square -> square, this.plantingService::getPlantingsBySquare));
    }

    @Override
    @Transactional
    public Integer getGardenRemainingSurface(Garden garden) {
        List<Square> squareList = this.getSquaresByGarden(garden);
        return squareList.isEmpty() ? garden.getGardenSurface() : garden.getGardenSurface() - squareList.stream().mapToInt(Square::getSquareSurface).sum();
    }

    @Override
    @Transactional
    public Map<Square, Integer> getSquareRemainingSurfaceBySquare(Garden garden){
        return this.getSquaresByGarden(garden).stream().collect(Collectors.toMap(square -> square, this.plantingService::getSquareRemainingSurface));

    }

    @Override
    @Transactional
    public Map<Square, List<Planting>> getMapPlantingsBySquare(boolean emptySquare,Plant plant) {
        return this.getSquares().stream()
                .filter(square -> !emptySquare || this.plantingService.getPlantingsBySquare(square).isEmpty())
                .filter(square -> plant == null || this.plantingService.getPlantingsBySquare(square).stream().anyMatch(planting -> planting.getPlant().equals(plant)))
                .collect(Collectors.toMap(
                s -> s,
                s -> this.plantingService.getPlantingsBySquare(s).stream()
                        .filter(planting -> plant == null || plant.equals(planting.getPlant()))
                        .collect(Collectors.toList())
        ));
    }

}
