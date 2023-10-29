package intergration.feed.config;

import intergration.feed.account.AccountRepository;
import intergration.feed.config.jwt.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccountRepository accountRepository;
    @Value("${spring.jwt.secret-key}")
    private String jwtKey;

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .httpBasic().disable()
            .addFilterBefore(new JwtTokenFilter(accountRepository, jwtKey),
                UsernamePasswordAuthenticationFilter.class)
            .formLogin()
            .loginProcessingUrl("/login");
    }
}
