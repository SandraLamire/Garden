package fr.eni.garden.service;

import fr.eni.garden.entity.Garden;
import fr.eni.garden.entity.Plant;
import fr.eni.garden.entity.Planting;
import fr.eni.garden.entity.Square;
import fr.eni.garden.exception.SquareException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SquareService {

    Optional<Square> getSquare(Integer idSquare);

    List<Square> getSquares();

    void addSquare(Square square) throws SquareException;

    void editSquare(Square square) throws SquareException;

    void deleteSquare(Square square);

    List<Square> getSquaresByGarden(Garden garden);

    Map<Square, List<Planting>> getPlantingsBySquare(Garden garden);

    Integer getGardenRemainingSurface(Garden garden);

    Map<Square, Integer> getSquareRemainingSurfaceBySquare(Garden garden);

    Map<Square, List<Planting>> getMapPlantingsBySquare(boolean emptySquare, Plant plant);

    boolean isSquareEmpty(Square square);

    boolean isAnySquareEmpty(List<Square> squares);

    boolean isSquareHasPlantingWithPlant(Square square, Plant plant);

    boolean isAnySquareHasPlantingWithPlant(List<Square> squares, Plant plant);
}
