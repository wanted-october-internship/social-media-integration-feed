package intergration.feed.cert;

import intergration.feed.account.domain.Account;
import intergration.feed.cert.domain.Cert;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertRepository extends JpaRepository<Cert, Long> {

    Optional<Cert> findByAccount(Account account);
}
