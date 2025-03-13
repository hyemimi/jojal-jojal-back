package community.Jojal_Jojal.controller;
import community.Jojal_Jojal.entity.User;
import community.Jojal_Jojal.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> getUserById(@PathVariable("user_id") Long user_id) {
        Optional<User> user = userService.getUserById(user_id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}