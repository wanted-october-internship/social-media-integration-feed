package intergration.feed.account.domain;

import intergration.feed.account.domain.type.JoinStatus;
import intergration.feed.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String loginId;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(value = EnumType.STRING)
    private JoinStatus joinStatus;

    public Account(String loginId, String email, String password) {
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        joinStatus = JoinStatus.READY;
    }
    public void successJoin() {
        joinStatus = JoinStatus.JOIN;
    }
}
