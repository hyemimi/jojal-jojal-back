package community.Jojal_Jojal.dto.response;
import community.Jojal_Jojal.entity.User;
import lombok.Getter;

@Getter
public class UserUpdateResponse {
    private String nickname;
    private String profileImage;

    public UserUpdateResponse(User user) {
        this.nickname = user.getNickname();
        this.profileImage = user.getProfile_image();
    }

}