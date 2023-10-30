package intergration.feed.app.post.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SnsType {
    FACEBOOK("페이스북", "https://www.facebook.com"), TWITTER("트위터",
        "https://www.twitter.com"), INSTAGRAM("인스타그램", "https://www.instagram.com"), THREADS("스레드",
        "https://www.threads.net");
    private final String name;
    private final String url;
}
