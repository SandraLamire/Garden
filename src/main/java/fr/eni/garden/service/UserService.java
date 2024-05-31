package fr.eni.garden.service;


import fr.eni.garden.entity.User;
import fr.eni.garden.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    @Transactional
    public void addUser(User User) {
        User.setPassword(this.encoder.encode(User.getPassword()));
        this.userRepository.save(User);
    }

    public List<User> getAllUsers() {
        return (List<User>) this.userRepository.findAll();
    }

}
