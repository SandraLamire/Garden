package fr.eni.garden.service;

import fr.eni.garden.entity.Square;
import fr.eni.garden.exception.SquareException;
import fr.eni.garden.repository.SquareRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    public Optional<Square> getOne(Integer idSquare) {
        return this.squareRepository.findById(idSquare);
    }

    @Override
    @Transactional
    public List<Square> getAll() {
        return (List<Square>) this.squareRepository.findAll();
    }

    @Override
    @Transactional
    public void addSquare(Square square) throws SquareException{
        if (square.getGarden().getGardenRemainingSurface() < square.getSquareSurface()) {
            throw new SquareException("There is not enough surface in this garden");
        }
        this.squareRepository.save(square);
    }

    @Override
    @Transactional
    public void editSquare(Square square) throws SquareException {
        Integer registeredSquareSurface = this.getOne(square.getIdSquare()).map(Square::getSquareSurface).orElse(0);
        if (square.getGarden().getGardenRemainingSurface() + registeredSquareSurface < square.getSquareSurface()) {
            throw new SquareException("There is not enough surface in this garden");
        }
        this.squareRepository.save(square);
    }

    @Override
    @Transactional
    public void deleteSquare(Square square) {
        square.getPlantingList().forEach(this.plantingService::deletePlanting);
        this.squareRepository.delete(square);
    }
}
