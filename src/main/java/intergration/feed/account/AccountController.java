package intergration.feed.account;

import intergration.feed.account.dto.AccountRequestDto.Join;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountWriteService accountWriteService;
    @PostMapping
    public ResponseEntity<String> join(@Valid @RequestBody Join join){
        accountWriteService.join(join);
        return ResponseEntity.status(201).body("회원가입 완료");
    }

}
