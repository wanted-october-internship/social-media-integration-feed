package intergration.feed.app.post;

import static intergration.feed.common.error.wanted.ErrorCode.NOT_FOUND_POST;

import intergration.feed.app.post.domain.Post;
import intergration.feed.app.post.dto.PostRequestDto.Filter;
import intergration.feed.common.error.wanted.WantedException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostReadService {

    private final PostRepository postRepository;

    public Post viewDetail(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new WantedException(NOT_FOUND_POST));
    }

    public Page<Post> getList(String loginId, Filter filter) {
        List<Post> posts = postRepository.findAllPost(filter, loginId).stream().filter(post -> {
            if(filter.getHashTag() != null) {
                return post.getHashTagList().contains(filter.getHashTag());
            }else {
                return post.getHashTagList().contains(loginId);
            }
        }
        ).collect(Collectors.toList());
        PageRequest pageRequest = PageRequest.of(filter.getPage(), filter.getPageCount());
        return new PageImpl<>(posts, pageRequest, posts.size());
    }
}
