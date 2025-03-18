package community.Jojal_Jojal.dto.post;

import community.Jojal_Jojal.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

public class PostResponseDto {

    @Getter
    public static class getAllPostsResponse {
        private Long postId;
        private String title;
        private int likeCount;
        private int viewsCount;
        private int commentCount;
        private LocalDateTime createdAt;

        private String nickname;
        private String profileImageUrl;

        public getAllPostsResponse(Post post) {
            postId = post.getId();
            title = post.getTitle();
            likeCount = post.getLikeCount();
            viewsCount = post.getViewsCount();
            createdAt = post.getCreatedAt();
            nickname = post.getUser().getNickname();
            profileImageUrl = post.getUser().getProfileImageUrl();
        }
    }



}
