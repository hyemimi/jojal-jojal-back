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


      @NotBlank
      private String post_content;

      @NotNull
      private Long user_id;
    }

    @Getter @Setter
    public static class editPost {
        @NotBlank
        private String title;

        @NotBlank
        private String post_content;

    }
}


