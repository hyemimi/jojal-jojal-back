package community.Jojal_Jojal.controller;
import community.Jojal_Jojal.dto.user.UserRequestDto;
import community.Jojal_Jojal.dto.user.UserResponseDto;
import community.Jojal_Jojal.entity.Post;
import community.Jojal_Jojal.entity.User;
import community.Jojal_Jojal.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /** 회원가입 */
    @Operation(summary = "회원가입")
    @PostMapping("")
    public ResponseEntity<UserResponseDto.RegisterResponse> registerUser(
            @ModelAttribute UserRequestDto.Register request, // form-data 필드 직접 매핑
            @RequestPart(value = "profile_image_url", required = false) MultipartFile profile_image_url)  {
        User user = userService.registerUser(request,profile_image_url);
        return ResponseEntity.ok(new UserResponseDto.RegisterResponse(user));
    }



    /** 토큰 발급 */

    /** 특정 유저 조회 */
    @Operation(summary = "유저 정보 조회")
    @GetMapping("/{user_id}")
    public ResponseEntity<UserResponseDto.UserDetail> getUserById(@PathVariable("user_id") Long userId) {

        return userService.getUserById(userId)
                .map(user -> ResponseEntity.ok(new UserResponseDto.UserDetail(user))) //
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404
    }


    /** 유저 정보 수정 */
    @Operation(summary = "유저 정보 수정")
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
    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/{user_id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("user_id") Long user_id){
        userService.deleteUser(user_id);
        return ResponseEntity.noContent().build();
    }

    /** 유저가 작성한 게시글 조회 */
    @GetMapping("/{user_id}/posts")
    @Operation(summary = "유저 작성 게시글 조회", description = "유저가 작성한 게시글을 조회합니다.")
    public ResponseEntity<List<UserResponseDto.getUserPosts>> getUserPosts(@PathVariable("user_id") Long id) {
        return ResponseEntity.ok(userService.getUserPosts(id));
    }


}