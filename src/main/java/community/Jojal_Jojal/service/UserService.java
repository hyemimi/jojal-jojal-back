package community.Jojal_Jojal.service;
import community.Jojal_Jojal.dto.post.PostResponseDto;
import community.Jojal_Jojal.dto.user.UserRequestDto;
import community.Jojal_Jojal.dto.user.UserResponseDto;
import community.Jojal_Jojal.entity.Post;
import community.Jojal_Jojal.entity.User;
import community.Jojal_Jojal.repository.PostRepository;
import community.Jojal_Jojal.repository.UserRepository;
import community.Jojal_Jojal.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;
    private final S3Uploader s3Uploader;


    /** 회원가입 */
    public User registerUser(UserRequestDto.Register request, MultipartFile profile_image_url)  {

        String imageUrl = null;

        try {
            imageUrl = s3Uploader.upload(profile_image_url, "profile-images");
        } catch (IOException e) {
            throw new RuntimeException("이미지 업로드 실패", e);
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .profile_image_url(imageUrl) // 여기서 직접 세팅
                .build();

        return userRepository.save(user);
    }

    /** 로그인 */

    /** 토큰 발급 */

    /** 특정 유저 조회 - id 기반 */
    public Optional<User> getUserById(Long user_id) {return userRepository.findById(user_id);}

    /** 유저 정보 수정 */
   @Transactional
    public User updateUser(Long user_id, UserRequestDto.EditProfile request, MultipartFile profile_image_url) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        user.setNickname(request.getNickname()); // null 허용 X

       if (profile_image_url != null && !profile_image_url.isEmpty()) {
           try {
               String imageUrl = s3Uploader.upload(profile_image_url, "profile-images");
               user.setProfile_image_url(imageUrl); // 기존 이미지 덮어쓰기
           } catch (IOException e) {
               throw new RuntimeException("프로필 이미지 업로드 실패", e);
           }
       }

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
        user.softDelete();
        userRepository.save(user);

        return user;
    }

    /** 유저가 작성한 게시글 조회 */
    @Transactional
    public List<UserResponseDto.getUserPosts> getUserPosts(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));

        return user.getPosts().stream()
                .map(post -> new UserResponseDto.getUserPosts(
                     post
                ))
                .collect(Collectors.toList());
    }


}