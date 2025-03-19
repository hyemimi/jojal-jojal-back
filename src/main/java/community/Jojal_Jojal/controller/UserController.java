package community.Jojal_Jojal.controller;
import community.Jojal_Jojal.dto.user.UserRequestDto;
import community.Jojal_Jojal.dto.user.UserResponseDto;
import community.Jojal_Jojal.entity.User;
import community.Jojal_Jojal.service.PostService;
import community.Jojal_Jojal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** 회원가입 */
    @PostMapping("")
    public ResponseEntity<UserResponseDto.RegisterResponse> registerUser(@RequestBody @Valid UserRequestDto.Register request) {
        User user = userService.registerUser(request);
        return ResponseEntity.ok(new UserResponseDto.RegisterResponse(user));
    }

    /** 로그인 */
    /** 토큰 발급 */

    /** 특정 유저 조회 */
    @GetMapping("/{user_id}")
    public ResponseEntity<UserResponseDto.UserDetail> getUserById(@PathVariable("user_id") Long userId) {

        return userService.getUserById(userId)
                .map(user -> ResponseEntity.ok(new UserResponseDto.UserDetail(user))) //
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404
    }


    /** 유저 정보 수정 */
   @PatchMapping("/{user_id}")
    public ResponseEntity<UserResponseDto.EditUserDetail> updateUser(
            @PathVariable("user_id") Long user_id,
           @RequestBody @Valid UserRequestDto.EditProfile request
    ) {
       User user = userService.updateUser(user_id, request);

       return ResponseEntity.ok(new UserResponseDto.EditUserDetail(user));
   }

    /** 유저 비밀번호 수정 */


    /** 회원 탈퇴 */
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user_id") Long user_id){
        userService.deleteUser(user_id);
        return ResponseEntity.noContent().build();
    }


}