package community.Jojal_Jojal.service;
import community.Jojal_Jojal.dto.post.PostRequestDto;
import community.Jojal_Jojal.dto.post.PostResponseDto;
import community.Jojal_Jojal.entity.Post;
import community.Jojal_Jojal.entity.User;
import community.Jojal_Jojal.repository.PostRepository;
import community.Jojal_Jojal.repository.UserRepository;
import community.Jojal_Jojal.util.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final S3Uploader s3Uploader;

    /** 게시물 전체 조회 */
    public List<PostResponseDto.getAllPostsResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()// 리스트 -> 스트림 변환
                .map(post -> new PostResponseDto.getAllPostsResponse(post))
                .collect(Collectors.toList()); // 리스트로 수집하여 변환
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    /** 게시물 생성 */
    @Transactional
    public Post uploadPost(PostRequestDto.uploadPost postDetails, MultipartFile post_image_url) {

        String imageUrl = null;

        try {
            imageUrl = s3Uploader.upload(post_image_url, "post-images");
        } catch (IOException e) {
            throw new RuntimeException("이미지 업로드 실패", e);
        }

        Post post = Post.builder()
                .user(userRepository.findById(postDetails.getUser_id())
                        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")))
                .title(postDetails.getTitle())
                .post_image_url(imageUrl)
                .post_content(postDetails.getPost_content())
                .build();
        return postRepository.save(post);
    }

    /** 게시글 삭제 */
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    /** 게시물 편집  */
    @Transactional
    public void updatePost(Long id, PostRequestDto.editPost editDetails) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        post.setTitle(editDetails.getTitle());
        post.setPost_content(editDetails.getPost_content());
        //post.setPost_image_url(editDetails.getPost_image_url());
        postRepository.save(post);

    }

    /** 좋아요 추가 */
    @Transactional
    public void updateHeart(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        post.postHeart();

        postRepository.save(post);

    }

    /** 좋아요 삭제 */
    @Transactional
    public void deleteHeart(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        post.deleteHeart();
        postRepository.save(post);
    }




}
