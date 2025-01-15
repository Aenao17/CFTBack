package ubb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ubb.model.DTO.LeaderboardDTO;
import ubb.model.DTO.UserLoginDTO;
import ubb.model.DTO.UserRegisterDTO;
import ubb.model.User;
import ubb.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> login(UserLoginDTO user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUserFromDTO(UserRegisterDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setScore(0);
        return user;
    }

    public List<LeaderboardDTO> getLeaderboard() {
        List<User> users = userRepository.findAll();
        List<LeaderboardDTO> leaderboard = new ArrayList<>();
        for (User user : users) {
            leaderboard.add(new LeaderboardDTO(user.getUsername(), user.getScore()));
        }
        return leaderboard;
    }
}
