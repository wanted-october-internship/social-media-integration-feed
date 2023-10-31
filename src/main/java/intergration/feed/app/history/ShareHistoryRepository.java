package intergration.feed.app.history;

import intergration.feed.app.account.domain.Account;
import intergration.feed.app.history.domain.ShareHistory;
import intergration.feed.app.post.domain.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareHistoryRepository extends JpaRepository<ShareHistory, Long> {
    int countByAccountAndPost(Account account, Post post);
    void deleteByAccountAndPost(Account account, Post post);

    List<ShareHistory> findAllByAccountAndPostIsNotNullOrderByCreatedAtDesc(Account account);
}
