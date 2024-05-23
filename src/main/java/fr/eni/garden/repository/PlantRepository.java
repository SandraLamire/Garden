package fr.eni.garden.repository;

import fr.eni.garden.entity.Plant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface PlantRepository extends CrudRepository<Plant, Integer> {
}
