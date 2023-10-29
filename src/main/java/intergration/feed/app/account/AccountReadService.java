package intergration.feed.app.account;

import static intergration.feed.common.error.wanted.ErrorCode.NOT_EQUALS_LOGIN_INFO;

import intergration.feed.app.account.domain.Account;
import intergration.feed.app.account.dto.AccountRequestDto.Login;
import intergration.feed.common.error.wanted.WantedException;
import intergration.feed.config.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountReadService {

    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Value("${spring.jwt.secret-key}")
    private  String jwtKey;

    public String executeLogin(Login login) {
        Account loginRequestAccont = accountRepository.findByLoginId(login.getLoginId())
            .orElseThrow(() -> new WantedException(NOT_EQUALS_LOGIN_INFO));
        if(!bCryptPasswordEncoder.matches(login.getPassword(),loginRequestAccont.getPassword())) {
            throw new WantedException(NOT_EQUALS_LOGIN_INFO);
        }
        return  JwtTokenUtil.create(login.getLoginId(), jwtKey, 30000);

    }

}
