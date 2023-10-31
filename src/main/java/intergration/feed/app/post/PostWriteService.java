package intergration.feed.app.post;

import static intergration.feed.common.error.wanted.ErrorCode.NOT_FOUND_ACCOUNT;
import static intergration.feed.common.error.wanted.ErrorCode.NOT_FOUND_POST;

import intergration.feed.app.account.AccountRepository;
import intergration.feed.app.account.domain.Account;
import intergration.feed.app.account.domain.type.JoinStatus;
import intergration.feed.app.history.ShareHistoryRepository;
import intergration.feed.app.history.ViewHistoryRepository;
import intergration.feed.app.history.domain.ShareHistory;
import intergration.feed.app.history.domain.ViewHistory;
import intergration.feed.app.post.domain.Post;
import intergration.feed.app.post.dto.PostRequestDto;
import intergration.feed.app.sns.SnsService;
import intergration.feed.common.error.wanted.WantedException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostWriteService {

    private final PostRepository postRepository;
    private final AccountRepository accountRepository;
    private final Map<String, SnsService> snsServiceMap;
    private final ViewHistoryRepository viewHistoryRepository;
    private final ShareHistoryRepository shareHistoryRepository;


    public void create(PostRequestDto.Create create) {
        Post post = create.toEntity();
        postRepository.save(post);
    }

    public void updateViewCount(Post post) {
        post.updateViewCount();
    }

    public void like(Long id, String loginId) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new WantedException(NOT_FOUND_POST));
        Account account = accountRepository.findByLoginIdAndJoinStatus(loginId, JoinStatus.JOIN)
            .orElseThrow(() -> new WantedException(NOT_FOUND_ACCOUNT));

        String snsName = post.getSns();
        SnsService snsService = snsServiceMap.get(snsName);

        boolean isLike = snsService.like(post.getId());
        if (isLike) {
            int count = viewHistoryRepository.countByAccountAndPost(account, post);

                post.updateLikeCount(count);
            if (count == 0) {
                viewHistoryRepository.save(ViewHistory.create(account, post));
            }else {
                viewHistoryRepository.deleteByAccountAndPost(account,post);
            }
        }
    }

    public void share(Long id, String loginId) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new WantedException(NOT_FOUND_POST));
        Account account = accountRepository.findByLoginIdAndJoinStatus(loginId, JoinStatus.JOIN)
            .orElseThrow(() -> new WantedException(NOT_FOUND_ACCOUNT));
        shareHistoryRepository.save(ShareHistory.create(account, post));

        String snsName = post.getSns();
        SnsService snsService = snsServiceMap.get(snsName);

        boolean isLike = snsService.share(post.getId());
        if (isLike) {
            int count = shareHistoryRepository.countByAccountAndPost(account, post);
            post.updateShareCount(count);
            if (count == 0) {
                shareHistoryRepository.save(ShareHistory.create(account, post));
            } else {
                shareHistoryRepository.deleteByAccountAndPost(account, post);
            }
        }
    }
}
