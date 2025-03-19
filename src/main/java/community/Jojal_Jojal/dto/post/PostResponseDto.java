package community.Jojal_Jojal.dto.post;

import community.Jojal_Jojal.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class PostResponseDto {

    @Getter
    @Setter
    public static class getAllPostsResponse {
        private Long post_id;
        private String title;
        private int like_count;
        private int views_count;
        private int comment_count;
        private LocalDateTime created_at;

        private String nickname;
        private String profile_image_url;

        public getAllPostsResponse(Post post) {
            this.post_id = post.getId();
            this.title = post.getTitle();
            this.like_count = post.getLike_count();
            this.views_count = post.getViews_Count();
            this.created_at = post.getCreated_at();
            this.nickname = post.getUser().getNickname();
            this.profile_image_url = post.getUser().getProfile_image_url();
        }
    }

    @Getter
    @Setter
    public static class getPostResponse {
         private Long post_id;
         private String title;
        private Integer like_count;
        private Integer views_count;
        private Integer comment_count;
        private LocalDateTime created_at;
        private String nickname;
        private String profile_image_url;
        private String post_image_url;
        private String post_content;

        public getPostResponse(Post post) {
            this.post_id = post.getId();
            this.title = post.getTitle();
            this.like_count = post.getLike_count();
            this.views_count = post.getViews_Count();
            //this.comment_count = post.getCo;
            this.created_at = post.getCreated_at();
            this.post_image_url = post.getPost_image_url();
            this.post_content = post. getPost_content();
            this.nickname = post.getUser().getNickname();
            this.profile_image_url = post.getUser().getProfile_image_url();
        }
    }

}
