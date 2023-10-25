package intergration.feed.account;

import intergration.feed.account.domain.Account;
import intergration.feed.account.domain.type.JoinStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account,Long> {

    boolean existsByLoginId(String loginId);

    Optional<Account> findByLoginIdAndJoinStatus(String loginId, JoinStatus joinStatus);
}
