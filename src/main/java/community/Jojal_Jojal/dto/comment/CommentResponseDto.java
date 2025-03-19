package community.Jojal_Jojal.dto.comment;

import community.Jojal_Jojal.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class CommentResponseDto {

    @Getter
    @Setter
    public static class getComments {

        private Long comment_id;
        private String comment_content;
        private LocalDateTime created_at;
        private String nickname;
        private String profile_image_url;

        public getComments(Comment comment) {
            this.comment_id = comment.getId();
            this.comment_content = comment.getComment_content();
            this.created_at = comment.getCreated_at();
            this.nickname = comment.getUser().getNickname();
            this.profile_image_url = comment.getUser().getProfile_image_url();
        }

    }
}
