package intergration.feed.cert.domain;

import intergration.feed.account.domain.Account;
import intergration.feed.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Getter
@NoArgsConstructor
@Where(clause = "isApply = false")
public class Cert extends BaseEntity {

    @Column(nullable = false)
    private String code;
    @OneToOne(fetch = FetchType.LAZY)
    private Account account;
    private boolean isApply;

    public Cert(String code, Account account) {
        this.code = code;
        this.account = account;
    }
}
