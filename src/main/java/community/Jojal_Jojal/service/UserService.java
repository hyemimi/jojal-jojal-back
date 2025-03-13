package community.Jojal_Jojal.service;

import community.Jojal_Jojal.entity.User;
import community.Jojal_Jojal.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long user_id) {return userRepository.findById(user_id);}

}
