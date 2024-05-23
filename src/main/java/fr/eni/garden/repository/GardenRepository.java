package fr.eni.garden.repository;

import fr.eni.garden.entity.Garden;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GardenRepository extends CrudRepository<Garden, Integer> {

}
