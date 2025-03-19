package community.Jojal_Jojal.controller;

import community.Jojal_Jojal.dto.comment.CommentRequestDto;
import community.Jojal_Jojal.dto.comment.CommentResponseDto;
import community.Jojal_Jojal.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /** 댓글 작성 */
    @PostMapping("/{post_id}/comment")
    public ResponseEntity<Void> uploadComment (@RequestBody @Valid CommentRequestDto.uploadComment commentDetails) {
        commentService.uploadComment(commentDetails);

        return ResponseEntity.noContent().build();
    }

    /** 댓글 조회 */
    @GetMapping("/{post_id}/comment")
    public ResponseEntity<List<CommentResponseDto.getComments>> uploadComment (@PathVariable("post_id") Long id) {
        return ResponseEntity.ok(commentService.getComments(id));
    }

    /** 댓글 삭제 */
    @DeleteMapping("/{post_id}/comment/{comment_id}")
    public ResponseEntity<Void> deleteComment (@PathVariable("comment_id") Long comment_id) {

        commentService.deleteComment(comment_id);
        return ResponseEntity.noContent().build();
    }

    /** 댓글 수정 */
    @PatchMapping("/{post_id}/comment/{comment_id}")
    public ResponseEntity<Void> updateComment (@PathVariable("comment_id") Long id, @RequestBody @Valid CommentRequestDto.updateComment commentDetail) {
        commentService.updateComment(id, commentDetail);
        return ResponseEntity.noContent().build();
    }
}
