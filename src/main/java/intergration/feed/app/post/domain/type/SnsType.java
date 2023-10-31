package intergration.feed.app.post.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SnsType {
    FACEBOOK("페이스북", "https://www.facebook.com","facebook"), TWITTER("트위터",
        "https://www.twitter.com","twitter"), INSTAGRAM("인스타그램", "https://www.instagram.com","instagram"), THREADS("스레드",
        "https://www.threads.net","threads");
    private final String name;
    private final String url;
    private final String sns;

    public String getSns() {
        return sns;
    }
}
