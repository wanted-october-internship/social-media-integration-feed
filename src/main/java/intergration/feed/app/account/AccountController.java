package intergration.feed.app.account;

import intergration.feed.app.account.dto.AccountRequestDto.Login;
import intergration.feed.app.account.dto.AccountRequestDto.ValidateCertCode;
import intergration.feed.app.account.dto.AccountRequestDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountWriteService accountWriteService;
    private final AccountReadService accountReadService;

    @PostMapping
    public ResponseEntity<String> join(@Valid @RequestBody AccountRequestDto.Join join){
        accountWriteService.join(join);
        return ResponseEntity.status(201).body("회원가입 완료");
    }

    @GetMapping
    public void validateCertCode(ValidateCertCode validateCertCodeRequest) {
        accountWriteService.validateCertCode(validateCertCodeRequest);
    }

    @PostMapping("/login")
    public String executeLogin(@RequestBody Login login) {
        return accountReadService.executeLogin(login);
    }
}
