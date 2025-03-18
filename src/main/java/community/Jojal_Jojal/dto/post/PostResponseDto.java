package community.Jojal_Jojal.dto.post;

import community.Jojal_Jojal.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

public class PostResponseDto {

    @Getter
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
            post_id = post.getId();
            title = post.getTitle();
            like_count = post.getLikeCount();
            views_count = post.getViewsCount();
            created_at = post.getCreatedAt();
            nickname = post.getUser().getNickname();
            profile_image_url = post.getUser().getProfileImageUrl();
        }
    }

    @Getter
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
            post_id = post.getId();
            title = post.getTitle();
            like_count = post.getLikeCount();
            views_count = post.getViewsCount();
            comment_count = post.getViewsCount();
            created_at = post.getCreatedAt();
            post_image_url = post.getPostImageUrl();
            post_content = post.getPostContent();
            nickname = post.getUser().getNickname();
            profile_image_url = post.getUser().getProfileImageUrl();
        }
    }

}
