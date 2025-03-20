package community.Jojal_Jojal.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public class AuthRequestDto {

    @Getter
    @Setter
    public static class loginUser {
        @NotBlank
        private String email;
        @NotBlank
        private String password;
    }
}
