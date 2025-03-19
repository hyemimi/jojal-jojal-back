package community.Jojal_Jojal.service;
import community.Jojal_Jojal.dto.post.PostRequestDto;
import community.Jojal_Jojal.dto.post.PostResponseDto;
import community.Jojal_Jojal.entity.Post;
import community.Jojal_Jojal.repository.PostRepository;
import community.Jojal_Jojal.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

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
    public Post uploadPost(PostRequestDto.uploadPost postDetails) {
        Post post = Post.builder()
                .user(userRepository.findById(postDetails.getUser_id())
                        .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")))
                .title(postDetails.getTitle())
                .post_image_url(postDetails.getPost_image_url())
                .post_content(postDetails.getPost_content())
                .build();
        return postRepository.save(post);
    }

    /** 게시글 삭제 */
    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
