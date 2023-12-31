package intergration.feed.app.account;

import static intergration.feed.common.error.wanted.ErrorCode.NOT_FOUND_ACCOUNT;
import static intergration.feed.common.error.wanted.ErrorCode.NOT_FOUND_CERT_CODE;

import intergration.feed.app.account.domain.Account;
import intergration.feed.app.account.domain.type.JoinStatus;
import intergration.feed.app.account.domain.type.Role;
import intergration.feed.app.account.dto.AccountRequestDto.Join;
import intergration.feed.app.account.dto.AccountRequestDto.ValidateCertCode;
import intergration.feed.app.cert.CertRepository;
import intergration.feed.app.cert.domain.Cert;
import intergration.feed.app.cert.domain.RandomCertCode;
import intergration.feed.common.error.wanted.ErrorCode;
import intergration.feed.common.error.wanted.WantedException;
import intergration.feed.validate.Password;
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
        Password.validate(join.getPassword());
        join.encryptPassword(bCryptPasswordEncoder);
        Account account = join.toEntity(Role.USER);
        accountRepository.save(account);
        certRepository.save(RandomCertCode.create(account));
    }

    public void  validateCertCode(ValidateCertCode validateCertCodeRequest) {
        Account account = accountRepository.findByLoginIdAndJoinStatus(validateCertCodeRequest.getLoginId(), JoinStatus.READY)
            .orElseThrow(() -> new WantedException(NOT_FOUND_ACCOUNT));
        Cert cert = certRepository.findByAccount(account)
            .orElseThrow(() -> new WantedException(NOT_FOUND_CERT_CODE));
        validateCertCodeRequest.equalsCode(cert);
        account.successJoin();
    }
}
