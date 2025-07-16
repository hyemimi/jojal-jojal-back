package community.Jojal_Jojal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String post_content;

    private String post_image_url;

    @Column(nullable = false)
    private int like_count = 0;

    @Column(nullable = false)
    private int views_Count = 0;

    @Column(nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime created_at = LocalDateTime.now();

    private LocalDateTime updated_at;
    @PreUpdate
    public void preUpdate() {
        this.updated_at = LocalDateTime.now();
    }

    public void postHeart() {
        this.like_count += 1;
    }

    public void deleteHeart() {
        if (this.like_count == 0) return;

        this.like_count -= 1;
    }

    // 양방향 관계 설정 (1:N)
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
