package intergration.feed.app.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AccountResponseDto {
    @Getter
    @AllArgsConstructor
    public static class LoginInfo {
        private String loginId;
    }
}
