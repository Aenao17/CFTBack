package ubb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ubb.auth.AuthenticationRequest;
import ubb.auth.AuthenticationResponse;
import ubb.model.DTO.LeaderboardDTO;
import ubb.model.DTO.UserLoginDTO;
import ubb.model.DTO.UserRegisterDTO;
import ubb.model.Token;
import ubb.model.User;
import ubb.service.AuthenticationService;
import ubb.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authService;

    @Autowired
    public UserController(UserService userService, AuthenticationService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/u/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<LeaderboardDTO>> getLeaderboard() {
        return ResponseEntity.ok(userService.getLeaderboard());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserLoginDTO loginDTO) {
        Optional<User> foundUser = userService.login(loginDTO);
        if (foundUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authService.authenticate(new AuthenticationRequest(loginDTO.getUsername(), loginDTO.getPassword())));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserRegisterDTO> signup(@RequestBody UserRegisterDTO signupDTO) {
        User user = userService.createUserFromDTO(signupDTO);
        return ResponseEntity.ok(UserRegisterDTO.convertToDTO(userService.saveUser(user)));
    }

    @RequestMapping(value = "/token/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Token>> getAllTokensOfUser(@PathVariable Long id) {
        var user = userService.findUserById(id);
        return user.map(value -> ResponseEntity.of(Optional.ofNullable(value.getTokens())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
