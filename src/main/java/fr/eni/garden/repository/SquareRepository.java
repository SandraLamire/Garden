package fr.eni.garden.repository;

import fr.eni.garden.entity.Square;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SquareRepository extends CrudRepository<Square, Integer> {
}
