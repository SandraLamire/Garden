package fr.eni.garden.repository;

import fr.eni.garden.entity.Garden;
import fr.eni.garden.entity.Square;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SquareRepository extends CrudRepository<Square, Integer> {
    List<Square> findAllByGarden(Garden garden);
}
