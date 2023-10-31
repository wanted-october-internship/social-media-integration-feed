package intergration.feed.app.history;

import intergration.feed.app.account.domain.Account;
import intergration.feed.app.post.domain.Post;
import intergration.feed.common.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class ViewHistory extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

}
