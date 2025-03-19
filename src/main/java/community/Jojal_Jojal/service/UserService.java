package community.Jojal_Jojal.service;
import community.Jojal_Jojal.dto.user.UserRequestDto;
import community.Jojal_Jojal.dto.user.UserResponseDto;
import community.Jojal_Jojal.entity.User;
import community.Jojal_Jojal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    /** 회원가입 */
    public User registerUser(UserRequestDto.Register request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .profile_image_url(request.getProfile_image_url())
                .build();
       userRepository.save(user);

       return user;
    }

    /** 로그인 */

    /** 토큰 발급 */

    /** 특정 유저 조회 - id 기반 */
    public Optional<User> getUserById(Long user_id) {return userRepository.findById(user_id);}

    /** 유저 정보 수정 */
   @Transactional
    public User updateUser(Long user_id, UserRequestDto.EditProfile request) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        user.setNickname(request.getNickname()); // null 허용 X
        user.setProfile_image_url(request.getProfile_image_url()); // null 허용
        userRepository.save(user);

        return user;
    }

    /** 유저 비밀번호 수정 */
    @Transactional
    public User updatePassword(Long user_id, UserRequestDto.EditPassword request) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        user.setPassword(request.getPassword());
        userRepository.save(user);

        return user;
    }

    /** 회원 탈퇴 */
    @Transactional
    public User deleteUser(Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

       // user.setDeletedAt();
        userRepository.delete(user);

        return user;
    }

}
