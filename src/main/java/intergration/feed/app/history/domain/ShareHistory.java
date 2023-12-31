package intergration.feed.app.history.domain;

import intergration.feed.app.account.domain.Account;
import intergration.feed.app.post.domain.Post;
import intergration.feed.common.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShareHistory extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
    @OneToOne(fetch = FetchType.LAZY)
    private Post post;

    private ShareHistory(Account account, Post post) {
        this.account = account;
        this.post = post;
    }

    public static ShareHistory create(Account account, Post post) {
        return new ShareHistory(account,post);
    }
}
