package intergration.feed.app.history;

import intergration.feed.app.account.domain.Account;
import intergration.feed.app.history.domain.ViewHistory;
import intergration.feed.app.post.domain.Post;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {

    int countByAccountAndPost(Account account, Post post);

    List<ViewHistory> findAllByAccountAndPostIsNotNullOrderByCreatedAtDesc(Account account);
}
