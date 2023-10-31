package intergration.feed.app.sns;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component(value = "threads")
public class ThreadsService implements SnsService {
    @Value("${sns.threads.like-url}")
    private String likeUrl;
    @Value("${sns.threads.share-url}")
    private String shareUrl;
    @Value("${spring.local}")
    private boolean isLocal;
    private RestTemplate restTemplate;
    @Override
    public boolean like(Long postId) {
        try {
            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
            parameters.add("jeong", "test");
            restTemplate.postForEntity(likeUrl + postId, parameters, String.class);
            return true;
        }catch (Exception e) {
            if(isLocal) {
                return true;
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean share(Long postId) {
        try {
            MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
            parameters.add("jeong", "test");
            restTemplate.postForEntity(shareUrl + postId, parameters, String.class);
            return true;
        }catch (Exception e) {
            if(isLocal) {
                return true;
            }
            e.printStackTrace();
            return false;
        }
    }
}
