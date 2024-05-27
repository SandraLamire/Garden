package fr.eni.garden.repository;

import fr.eni.garden.entity.Plant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlantRepository extends CrudRepository<Plant, Integer> {

    Plant findByNameAndVariety(String name, String variety);

    List<Plant> findAllByName(String name);
}
