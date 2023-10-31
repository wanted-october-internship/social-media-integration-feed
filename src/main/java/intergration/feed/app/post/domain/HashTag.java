package intergration.feed.app.post.domain;

import intergration.feed.common.BaseEntity;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HashTag extends BaseEntity {

    private String title;

    private HashTag(String title) {
        this.title = title;
    }

    public static HashTag create(String title) {
        return new HashTag(title);
    }
}
