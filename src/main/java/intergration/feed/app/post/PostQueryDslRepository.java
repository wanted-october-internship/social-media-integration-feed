package intergration.feed.app.post;

import intergration.feed.app.post.domain.Post;
import intergration.feed.app.post.dto.PostRequestDto.Filter;
import java.util.List;

public interface PostQueryDslRepository {
    List<Post> findAllPost(Filter filter, String loginId);
}
