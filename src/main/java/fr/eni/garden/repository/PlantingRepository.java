package fr.eni.garden.repository;

import fr.eni.garden.entity.Planting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantingRepository extends CrudRepository<Planting, Integer> {

}
