package fr.eni.garden.repository;


import fr.eni.garden.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByPseudo(String pseudo);
}
