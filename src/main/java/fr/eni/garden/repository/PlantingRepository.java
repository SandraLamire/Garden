package fr.eni.garden.repository;

import fr.eni.garden.entity.Plant;
import fr.eni.garden.entity.Planting;
import fr.eni.garden.entity.Square;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantingRepository extends CrudRepository<Planting, Integer> {

    List<Planting> findAllByPlantIs(Plant plant);

    List<Planting> findAllBySquare(Square square);
}
