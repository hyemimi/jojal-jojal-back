package community.Jojal_Jojal.controller;

import community.Jojal_Jojal.dto.comment.CommentRequestDto;
import community.Jojal_Jojal.dto.comment.CommentResponseDto;
import community.Jojal_Jojal.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "댓글 작성", description = "post_id를 path variable로 입력 받아 댓글을 작성합니다. 정상 작성시 204코드를 반환합니다. ")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "성공적으로 작성됨 (No Content)")
    })
    @PostMapping("/{post_id}/comment")
    public ResponseEntity<Void> uploadComment (@RequestBody @Valid CommentRequestDto.uploadComment commentDetails) {
        commentService.uploadComment(commentDetails);

        return ResponseEntity.noContent().build();
    }
    /** 댓글 조회 */
    @Operation(summary = "게시글 댓글 조회", description = "post_id를 기반으로 댓글들을 조회합니다.")
    @GetMapping("/{post_id}/comment")
    public ResponseEntity<List<CommentResponseDto.getComments>> uploadComment (@PathVariable("post_id") Long id) {
        return ResponseEntity.ok(commentService.getComments(id));
    }

    /** 댓글 삭제 */
    @Operation(summary = "게시글 댓글 삭제", description = "댓글을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨")
    })
    @DeleteMapping("/{post_id}/comment/{comment_id}")
    public ResponseEntity<Void> deleteComment (@PathVariable("comment_id") Long comment_id) {

        commentService.deleteComment(comment_id);
        return ResponseEntity.noContent().build();
    }

    /** 댓글 수정 */
    @PatchMapping("/{post_id}/comment/{comment_id}")
    @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "성공적으로 수정됨")
    })
    public ResponseEntity<Void> updateComment (@PathVariable("comment_id") Long id, @RequestBody @Valid CommentRequestDto.updateComment commentDetail) {
        commentService.updateComment(id, commentDetail);
        return ResponseEntity.noContent().build();
    }
}
