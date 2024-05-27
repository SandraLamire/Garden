package fr.eni.garden.service;

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

    public SquareServiceImpl(SquareRepository squareRepository) {
        this.squareRepository = squareRepository;
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
        if (square.getGarden().getGardenRemainingSurface() < square.getSquareSurface()) {
            throw new SquareException("There is not enough surface in this garden");
        }
        this.squareRepository.save(square);    }

    @Override
    @Transactional
    public void deleteSquare(Square square) {
        this.squareRepository.delete(square);
    }

    @Override
    @Transactional
    public Map<String,Long> getPlantNameCounting(Square square) {
        return  square
                .getPlantingList()
                .stream()
                .map(Planting::getPlant)
                .collect(Collectors.groupingBy(Plant::getName, Collectors.counting()));
    }
}
