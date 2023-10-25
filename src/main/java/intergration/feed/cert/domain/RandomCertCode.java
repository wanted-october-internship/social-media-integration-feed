package intergration.feed.cert.domain;

import intergration.feed.account.domain.Account;
import java.util.UUID;

public class RandomCertCode {

    public static Cert create(Account account) {
        final String code = UUID.randomUUID().toString().substring(0,6);
        return new Cert(code,account);
    }
}
