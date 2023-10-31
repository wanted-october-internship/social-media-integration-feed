package intergration.feed.app.history;

import intergration.feed.app.history.domain.ViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViewHistoryRepository extends JpaRepository<ViewHistory, Long> {
}
