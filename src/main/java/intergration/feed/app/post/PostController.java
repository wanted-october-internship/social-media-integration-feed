package intergration.feed.app.post;

import intergration.feed.app.account.annotation.LoginCheck;
import intergration.feed.app.account.dto.AccountResponseDto.LoginInfo;
import intergration.feed.app.post.domain.Post;
import intergration.feed.app.post.dto.PostRequestDto;
import intergration.feed.app.post.dto.PostRequestDto.Filter;
import intergration.feed.app.post.dto.PostResponseDto.Detail;
import intergration.feed.app.post.dto.PostResponseDto.Posting;
import intergration.feed.app.post.dto.PostResponseDto.PostingList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostWriteService postWriteService;
    private final PostReadService postReadService;

    @PostMapping
    public void create(@RequestBody PostRequestDto.Create create) {
        postWriteService.create(create);
    }

    @GetMapping("/{postId}")
    public Detail viewDetail(@PathVariable(name = "postId") Long id) {
        Post post = postReadService.viewDetail(id);
        postWriteService.updateViewCount(post);
        return Detail.toResponse(post);
    }
    @GetMapping
    public Page<Posting> getList(Filter filter) {
        Page<Post> result = postReadService.getList(filter);
        return PostingList.toResponse(result);
    }
    @PostMapping("/like/{postId}")
    public void like(@PathVariable("postId")Long id) {
        postWriteService.like(id);
    }

    @PostMapping("/share/{postId}")
    public void share(@PathVariable("postId")Long id) {
        postWriteService.share(id);
    }
}
