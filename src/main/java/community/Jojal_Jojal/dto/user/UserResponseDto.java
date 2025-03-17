package community.Jojal_Jojal.dto.user;
import community.Jojal_Jojal.entity.User;
import lombok.Getter;

public class UserResponseDto {

    /** 회원가입 응답 */
    @Getter
    public static class RegisterResponse {
        private Long userId;
        private String message;

        public RegisterResponse(User user) {
            this.message = "register_success";
            this.userId = user.getId();
        }
    }

    /** 로그인 응답 */
    @Getter
    public static class LoginResponse {
        private Long userId;
        private String nickname;
        private String profileImageUrl;
        private String message = "login_success";

        public LoginResponse(User user) {
            this.userId = user.getId();
            this.nickname = user.getNickname();
            this.profileImageUrl = user.getProfileImageUrl();
        }
    }

    /** 특정 유저 정보 조회 응답 */
    @Getter
    public static class UserDetail {
        private String email;
        private String nickname;
        private String profileImageUrl;

        public UserDetail(User user) {
            this.email = user.getEmail();
            this.nickname = user.getNickname();
            this.profileImageUrl = user.getProfileImageUrl();
        }
    }

    /** 유저 정보 수정 응답 */
    @Getter
    public static class EditUserDetail {

        private String nickname;
        private String profileImageUrl;

        public EditUserDetail(User user) {
            this.nickname = user.getNickname();
            this.profileImageUrl = user.getProfileImageUrl();
        }
    }


}
