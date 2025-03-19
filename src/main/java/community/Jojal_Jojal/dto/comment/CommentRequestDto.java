package community.Jojal_Jojal.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Text;

import java.awt.font.TextAttribute;

public class CommentRequestDto {

    @Getter
    @Setter
    public static class uploadComment {

        @NotNull
        private Long post_id;

        @NotNull
        private Long user_id;

        @NotBlank
        private String comment_content;

    }
}
