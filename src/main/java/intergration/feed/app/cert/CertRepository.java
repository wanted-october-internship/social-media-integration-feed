package intergration.feed.app.cert;

import intergration.feed.app.account.domain.Account;
import intergration.feed.app.cert.domain.Cert;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertRepository extends JpaRepository<Cert, Long> {

    Optional<Cert> findByAccount(Account account);
}
