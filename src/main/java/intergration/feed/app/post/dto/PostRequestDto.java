package intergration.feed.app.post.dto;

import intergration.feed.app.account.domain.Account;
import intergration.feed.app.post.domain.HashTag;
import intergration.feed.app.post.domain.Post;
import intergration.feed.app.post.domain.type.SnsType;
import intergration.feed.app.post.dto.type.SortType;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class PostRequestDto {

    @Getter
    @AllArgsConstructor
    public static class Create {
        @NotNull
        private SnsType snsType;
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        @NotEmpty
        private List<String> hashTags;

        public Post toEntity() {
            List<HashTag> hashTagList = hashTags.stream().map(HashTag::create)
                .collect(Collectors.toList());
            return Post.create(snsType,title,content, hashTagList);
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Filter {
        private String hashTag;
        private SnsType snsType;
        private String orderBy;
        private String searchBy;
        private String search;
        private SortType sortType;
        private int page;
        private int pageCount = 10;
    }
}
