package intergration.feed.config.interceptor;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptor;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolver) {
        resolver.add(loginCheckInterceptor);
    }
}
