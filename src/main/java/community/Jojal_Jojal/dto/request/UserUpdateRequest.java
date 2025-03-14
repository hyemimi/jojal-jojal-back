package community.Jojal_Jojal.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UserUpdateRequest {

    @NotBlank(message = "닉네임은 필수입니다.")
    private String nickname;
    private String profile_image; // nullable 허용
}
