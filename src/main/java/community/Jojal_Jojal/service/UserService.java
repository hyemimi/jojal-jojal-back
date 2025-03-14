package community.Jojal_Jojal.service;

import community.Jojal_Jojal.dto.request.UserUpdateRequest;
import community.Jojal_Jojal.dto.response.UserUpdateResponse;
import community.Jojal_Jojal.entity.User;
import community.Jojal_Jojal.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** 회원가입 */
    /** 로그인 */
    /** 토큰 발급 */

    /** 특정 유저 조회 */
    public Optional<User> getUserById(Long user_id) {return userRepository.findById(user_id);}
    /** 유저 정보 수정 */
    @Transactional
    public UserUpdateResponse updateUser(Long user_id, UserUpdateRequest request) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        user.setNickname(request.getNickname()); // null 허용 X
        user.setProfile_image(request.getProfile_image()); // null 허용

        userRepository.save(user);

        return new UserUpdateResponse(user);
    }
    /** 유저 비밀번호 수정 */

    /** 회원 탈퇴 */

}
