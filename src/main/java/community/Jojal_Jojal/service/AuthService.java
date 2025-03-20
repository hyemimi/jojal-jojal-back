package community.Jojal_Jojal.service;

import community.Jojal_Jojal.dto.auth.AuthRequestDto;
import community.Jojal_Jojal.dto.auth.AuthResponseDto;
import community.Jojal_Jojal.entity.User;
import community.Jojal_Jojal.repository.UserRepository;
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
    public User loginUser (AuthRequestDto.loginUser userDetail) {

        Optional<User> userOptional = userRepository.findByEmail(userDetail.getEmail());

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("이메일이 존재하지 않습니다.");
        }

        User user = userOptional.get();
        // 비밀번호 검증 (해시 비교)
        if (!passwordEncoder.matches(userDetail.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공 시 사용자 정보 반환
        return user;
    }
}
