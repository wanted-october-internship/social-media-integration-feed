package intergration.feed.account.domain;

import intergration.feed.account.domain.type.JoinStatus;
import intergration.feed.account.domain.type.Role;
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
    @Enumerated(value = EnumType.STRING)
    private Role role;

    private Account(String loginId, String email, String password, JoinStatus joinStatus, Role role) {
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.joinStatus = joinStatus;
        this.role = role;
    }
    public static Account create(String loginId, String email, String password,Role role) {
        return new Account(loginId,email,password,JoinStatus.READY,role);
    }
    public void successJoin() {
        joinStatus = JoinStatus.JOIN;
    }
}
