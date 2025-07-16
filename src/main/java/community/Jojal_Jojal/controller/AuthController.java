package community.Jojal_Jojal.controller;

import community.Jojal_Jojal.dto.auth.AuthRequestDto;
import community.Jojal_Jojal.dto.auth.AuthResponseDto;
import community.Jojal_Jojal.service.AuthService;
import community.Jojal_Jojal.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {this.authService = authService;}

    @PostMapping("")
    @Operation(summary = "로그인")
    public ResponseEntity<AuthResponseDto.loginUser> loginUser (@RequestBody @Valid AuthRequestDto.loginUser loginDetail) {
        return ResponseEntity.ok(authService.loginUser(loginDetail));
    }
}
