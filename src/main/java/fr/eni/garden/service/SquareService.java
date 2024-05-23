package fr.eni.garden.service;

import fr.eni.garden.entity.Square;

import java.util.List;
import java.util.Optional;

public interface SquareService {

    Optional<Square> getOne(Integer idSquare);

    List<Square> getAll();

    void addSquare(Square square);

    void editSquare(Square square);

    void deleteSquare(Square square);
}
