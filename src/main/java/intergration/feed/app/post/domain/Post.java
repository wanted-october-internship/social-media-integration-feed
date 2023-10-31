package intergration.feed.app.post.domain;

import intergration.feed.app.post.domain.type.SnsType;
import intergration.feed.common.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private SnsType snsType;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    private long viewCount;
    private long likeCount;
    private long shareCount;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<HashTag> hashTags;

    private Post(SnsType snsType, String title, String content,
        List<HashTag> hashTags) {
        this.snsType = snsType;
        this.title = title;
        this.content = content;
        this.hashTags = hashTags;
    }

    public static Post create(SnsType snsType, String title, String content,
        List<HashTag> hashTags) {
        return new Post(snsType, title, content, hashTags);
    }

    public List<String> getHashTagList() {
        return hashTags.isEmpty() ? new ArrayList<>()
            : hashTags.stream().map(HashTag::getTitle).collect(
                Collectors.toList());
    }

    public void updateViewCount() {
        viewCount++;
    }

    public String getSns() {
        return snsType.getSns();
    }
    public void updateLikeCount() {
        likeCount++;
    }
}
