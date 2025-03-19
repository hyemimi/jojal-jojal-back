package community.Jojal_Jojal.controller;

import community.Jojal_Jojal.dto.comment.CommentRequestDto;
import community.Jojal_Jojal.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{post_id}/comment")
    public ResponseEntity<Void> uploadComment (@RequestBody @Valid CommentRequestDto.uploadComment commentDetails) {
        commentService.uploadComment(commentDetails);

        return ResponseEntity.noContent().build();
    }
}
