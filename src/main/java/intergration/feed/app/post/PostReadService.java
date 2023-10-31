package intergration.feed.app.post;

import static intergration.feed.common.error.wanted.ErrorCode.NOT_FOUND_POST;

import intergration.feed.app.post.domain.Post;
import intergration.feed.app.post.dto.PostRequestDto.Filter;
import intergration.feed.common.error.wanted.WantedException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostReadService {

    private final PostRepository postRepository;

    public Post viewDetail(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new WantedException(NOT_FOUND_POST));
    }


    public Page<Post> getList(Filter filter) {
        List<Post> posts = postRepository.findAllPost(filter);
        PageRequest pageRequest = PageRequest.of(filter.getPage(), filter.getPageCount());
        return new PageImpl<>(posts,pageRequest, posts.size());
    }
}
