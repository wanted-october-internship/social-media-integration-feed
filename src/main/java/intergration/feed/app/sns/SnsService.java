package intergration.feed.app.sns;

public interface SnsService {
    boolean like(Long postId);
    boolean share(Long postId);
}
