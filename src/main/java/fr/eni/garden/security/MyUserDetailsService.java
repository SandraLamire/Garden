package fr.eni.garden.security;

import fr.eni.garden.entity.User;
import fr.eni.garden.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String pseudo) {

        User user;
        try {
            user = userRepository.findByPseudo(pseudo);
            if (user == null) {
                throw new UsernameNotFoundException(pseudo);
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException(pseudo);
        }
        return new MyUserDetail(user);
    }
}