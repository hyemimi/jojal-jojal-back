package community.Jojal_Jojal.dto.post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class PostRequestDto {

    @Getter @Setter
    public static class uploadPost {

      @NotBlank
      private String title;

      private String post_image_url;

      @NotBlank
      private String post_content;

      @NotNull
      private Long user_id;

    }
}


