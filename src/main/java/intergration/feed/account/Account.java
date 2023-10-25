package intergration.feed.account;

import intergration.feed.common.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
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
}
