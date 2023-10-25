package intergration.feed.cert;

import intergration.feed.cert.domain.Cert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertRepository extends JpaRepository<Cert, Long> {
}
