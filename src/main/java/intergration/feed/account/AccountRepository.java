package intergration.feed.account;

import intergration.feed.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

    boolean existsByLoginId(String loginId);
}
