package community.Jojal_Jojal.controller;
import community.Jojal_Jojal.dto.post.PostRequestDto;
import community.Jojal_Jojal.dto.post.PostResponseDto;
import community.Jojal_Jojal.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 게시글 생성
    @PostMapping("")
    @Operation(summary = "게시글 생성", description = "게시글을 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "성공적으로 업로드됨")
    })
    public ResponseEntity<Void> uploadPost(@RequestBody @Valid PostRequestDto.uploadPost postDetails) {
        postService.uploadPost(postDetails);

        return ResponseEntity.noContent().build();
    }

     // 게시글 전체 조회
    @GetMapping
    @Operation(summary = "게시글 전체 조회", description = "게시글을 전체 조회합니다.")
    public ResponseEntity<List<PostResponseDto.getAllPostsResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    // 게시글 단일 조회
    @GetMapping("/{post_id}")
    @Operation(summary = "게시글 상세 조회", description = " post_id를 기반으로 게시글 상세를 조회합니다.")
    public ResponseEntity<PostResponseDto.getPostResponse> getPostById(@PathVariable("post_id") Long id) {

        return  postService.getPostById(id).map(post ->ResponseEntity.ok(new PostResponseDto.getPostResponse(post))).orElseGet(() -> ResponseEntity.notFound().build());

    }
//

//
    // 게시글 수정
    @PatchMapping("/{post_id}")
    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "성공적으로 수정됨")
    })
    public ResponseEntity<Void> updatePost(@PathVariable("post_id") Long post_id, @RequestBody PostRequestDto.editPost editDetails) {
        postService.updatePost(post_id, editDetails);

        return ResponseEntity.noContent().build();
    }
//
    // 게시글 삭제
    @DeleteMapping("/{post_id}")
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨")
    })
    public ResponseEntity<Void> deletePost(@PathVariable("post_id") Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
