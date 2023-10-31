package intergration.feed.app.post.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import intergration.feed.app.post.domain.Post;
import intergration.feed.app.post.domain.type.SnsType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

public class PostResponseDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Detail {
        private Long postId;
        private SnsType snsType;
        private String title;
        private String content;
        private long viewCount;
        private long likeCount;
        private long shareCount;
        private List<String> hashTags;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdAt;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime updatedAt;


        public static Detail toResponse(Post post) {
            return Detail.builder()
                .postId(post.getId())
                .snsType(post.getSnsType())
                .title(post.getTitle())
                .content(post.getContent())
                .viewCount(post.getViewCount())
                .likeCount(post.getLikeCount())
                .hashTags(post.getHashTagList())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();

        }
    }
    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Posting {
        private List<String> hashTag;
        private SnsType snsType;
        private String content;
        private long viewCount;
        private long likeCount;
        private long shareCount;
        private String title;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdAt;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime updatedAt;


        public static Posting toResponse(Post post) {
            return new Posting(post.getHashTagList(), post.getSnsType(),
                post.getContent(),post.getViewCount(), post.getLikeCount(), post.getShareCount(),post.getTitle(),post.getCreatedAt(),post.getUpdatedAt());
        }
    }

    @Getter
    @AllArgsConstructor
    public static class PostingList {

        public static Page<Posting> toResponse(Page<Post> posts) {
            return posts.map(Posting::toResponse);
        }
    }
}
