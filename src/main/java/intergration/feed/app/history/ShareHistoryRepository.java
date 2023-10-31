package intergration.feed.app.history;

import intergration.feed.app.history.domain.ShareHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareHistoryRepository extends JpaRepository<ShareHistory, Long> {
}
