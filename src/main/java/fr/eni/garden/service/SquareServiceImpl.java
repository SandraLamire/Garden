package fr.eni.garden.service;

import fr.eni.garden.entity.Square;
import fr.eni.garden.repository.SquareRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SquareServiceImpl implements SquareService {

    private final SquareRepository squareRepository;

    public SquareServiceImpl(SquareRepository squareRepository) {
        this.squareRepository = squareRepository;
    }

    @Override
    public Optional<Square> getOne(Integer idSquare) {
        return this.squareRepository.findById(idSquare);
    }

    @Override
    public List<Square> getAll() {
        return (List<Square>) this.squareRepository.findAll();
    }

    @Override
    public void add(Square square) {
        this.squareRepository.save(square);
    }

    @Override
    public void edit(Square square) {
        this.squareRepository.save(square);
    }

    @Override
    public void delete(Square square) {
        this.squareRepository.delete(square);
    }
}
