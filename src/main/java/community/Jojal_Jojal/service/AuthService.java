package community.Jojal_Jojal.service;

import community.Jojal_Jojal.dto.auth.AuthRequestDto;
import community.Jojal_Jojal.dto.auth.AuthResponseDto;
import community.Jojal_Jojal.entity.User;
import community.Jojal_Jojal.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**로그인*/
    public AuthResponseDto.loginUser loginUser (AuthRequestDto.loginUser userDetail) {

        User user = userRepository.findByEmail(userDetail.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "이메일이 존재하지 않습니다."));

        if (!passwordEncoder.matches(userDetail.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 틀렸습니다.");
        }

        return new AuthResponseDto.loginUser(user); // AuthResponseDto.loginUser 타입 반환
    }
}
