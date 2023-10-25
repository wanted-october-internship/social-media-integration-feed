package intergration.feed.account;

import intergration.feed.account.domain.Account;
import intergration.feed.account.dto.AccountRequestDto.Join;
import intergration.feed.cert.CertRepository;
import intergration.feed.cert.domain.RandomCertCode;
import intergration.feed.common.error.wanted.ErrorCode;
import intergration.feed.common.error.wanted.WantedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountWriteService {
    private final AccountRepository accountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CertRepository certRepository;

    public void join(Join join) {
        if(accountRepository.existsByLoginId(join.getLoginId())) {
            throw new WantedException(ErrorCode.ALREADY_LOGIN_ID);
        }
        join.encryptPassword(bCryptPasswordEncoder);
        Account account = join.toEntity();
        accountRepository.save(account);
        certRepository.save(RandomCertCode.create(account));
    }
}
