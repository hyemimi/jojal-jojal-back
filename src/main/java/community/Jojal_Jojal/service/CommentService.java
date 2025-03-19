package community.Jojal_Jojal.service;

import community.Jojal_Jojal.dto.comment.CommentRequestDto;
import community.Jojal_Jojal.dto.comment.CommentResponseDto;
import community.Jojal_Jojal.dto.post.PostResponseDto;
import community.Jojal_Jojal.entity.Comment;
import community.Jojal_Jojal.repository.CommentRepository;
import community.Jojal_Jojal.repository.PostRepository;
import community.Jojal_Jojal.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,PostRepository postRepository, UserRepository userRepository){
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    /** 댓글 작성 */
    @Transactional
    public void uploadComment (CommentRequestDto.uploadComment commentDetails) {
        Comment comment = Comment.builder()
                .post(postRepository.findById(commentDetails.getPost_id()).orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")))
                .user(userRepository.findById(commentDetails.getUser_id()).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")))
                .comment_content(commentDetails.getComment_content())
                .build();
        commentRepository.save(comment);

        return;

    }

    /** 댓글 조회 */
    public List<CommentResponseDto.getComments> getComments (Long id) {
        return commentRepository.findAll().stream().map(comment -> new CommentResponseDto.getComments(comment)).collect(Collectors.toList());
    }


}
