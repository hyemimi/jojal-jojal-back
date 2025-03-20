package community.Jojal_Jojal.dto.auth;

import community.Jojal_Jojal.entity.User;
import lombok.Getter;
import lombok.Setter;

public class AuthResponseDto {

    @Getter
    @Setter
    public static class loginUser {
        private Long user_id;
        private String nickname;
        private String profile_image_url;

        public loginUser(User user){
            this.user_id = user.getId();
            this.nickname = user.getNickname();
            this.profile_image_url = user.getProfile_image_url();
        }
    }
}
