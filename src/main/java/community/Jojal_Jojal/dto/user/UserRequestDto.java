package community.Jojal_Jojal.dto.user;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class UserRequestDto {

    @Getter @Setter
    public static class Register {

        @NotBlank(message = "이메일은 필수입니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;

        @NotBlank(message = "닉네임 필수입니다.")
        private String nickname;

        private String profile_image_url;
    }

    @Getter @Setter
    public static class Login {

        @NotBlank(message = "이메일은 필수입니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
    }

    @Getter @Setter
    public static class EditProfile {
        private String nickname;
        private String profile_image_url;
    }

    @Getter @Setter
    public static class EditPassword {
        @NotBlank(message = "비밀번호는 필수입니다.")
        private String password;
    }

}

