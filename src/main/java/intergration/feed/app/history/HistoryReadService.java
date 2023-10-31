package intergration.feed.app.history;

import static intergration.feed.common.error.wanted.ErrorCode.NOT_FOUND_ACCOUNT;

import intergration.feed.app.account.AccountRepository;
import intergration.feed.app.account.domain.Account;
import intergration.feed.app.account.domain.type.JoinStatus;
import intergration.feed.app.history.dto.type.HistoryType;
import intergration.feed.app.post.domain.Post;
import intergration.feed.app.post.dto.PostResponseDto;
import intergration.feed.app.post.dto.PostResponseDto.Posting;
import intergration.feed.common.error.wanted.WantedException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HistoryReadService {

    private final ShareHistoryRepository shareHistoryRepository;
    private final ViewHistoryRepository viewHistoryRepository;
    private final AccountRepository accountRepository;

    public List<Posting> getHistoies(String loginId, HistoryType historyType) {
        Account account = accountRepository.findByLoginIdAndJoinStatus(loginId, JoinStatus.JOIN)
            .orElseThrow(() -> new WantedException(NOT_FOUND_ACCOUNT));
        if (historyType != null) {
            if (historyType == HistoryType.SHARE) {
                return shareHistoryRepository.findAllByAccountAndPostIsNotNullOrderByCreatedAtDesc(account)
                    .stream().map(history -> {
                        Post post = history.getPost();
                        if (post != null) {
                            return PostResponseDto.Posting.toResponse(post);
                        }
                        return null;
                    }).collect(Collectors.toList());
            } else {
                return viewHistoryRepository.findAllByAccountAndPostIsNotNullOrderByCreatedAtDesc(
                        account)
                    .stream().map(history -> {
                        Post post = history.getPost();
                        return PostResponseDto.Posting.toResponse(post);
                    }).collect(Collectors.toList());
            }
        }
        return new ArrayList<>();
    }

}
