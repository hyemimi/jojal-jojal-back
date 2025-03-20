package community.Jojal_Jojal.service;

import community.Jojal_Jojal.dto.auth.AuthRequestDto;
import community.Jojal_Jojal.entity.User;
import community.Jojal_Jojal.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public ResponseEntity loginUser (AuthRequestDto.loginUser userDetail) {
        Optional<User> userOptional = userRepository.findByEmail(userDetail.getEmail());

        // 이메일이 존재하지 않는 경우 : 401 Unauthorized 반환
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = userOptional.get();

        // 비밀번호가 틀린 경우 : 401 Unauthorized 반환
        if (!passwordEncoder.matches(userDetail.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 로그인 성공 시 200
        return ResponseEntity.ok(user);
    }
}
