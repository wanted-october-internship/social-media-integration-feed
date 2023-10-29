package intergration.feed.app.cert.domain;

import intergration.feed.app.account.domain.Account;
import java.util.UUID;

public class RandomCertCode {

    public static Cert create(Account account) {
        final String code = UUID.randomUUID().toString().substring(0,6);
        return new Cert(code,account);
    }
}
