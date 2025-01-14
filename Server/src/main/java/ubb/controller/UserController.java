package ubb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ubb.model.DTO.UserLoginDTO;
import ubb.model.DTO.UserRegisterDTO;
import ubb.model.User;
import ubb.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    public ResponseEntity<User> login(@RequestBody UserLoginDTO loginDTO) {
        return userService.login(loginDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/signup")
    public ResponseEntity<UserRegisterDTO> signup(@RequestBody UserRegisterDTO signupDTO) {
        User user = userService.createUserFromDTO(signupDTO);
        return ResponseEntity.ok(UserRegisterDTO.convertToDTO(userService.saveUser(user)));
    }
}
