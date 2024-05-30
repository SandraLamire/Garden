package fr.eni.garden.service;

import fr.eni.garden.entity.Square;
import fr.eni.garden.exception.SquareException;
import java.util.List;
import java.util.Optional;

public interface SquareService {

    Optional<Square> getOne(Integer idSquare);

    List<Square> getAll();

    void addSquare(Square square) throws SquareException;

    void editSquare(Square square) throws SquareException;

    void deleteSquare(Square square);
}
