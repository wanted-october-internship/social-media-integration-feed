package intergration.feed.account.dto;

import intergration.feed.account.domain.Account;
import intergration.feed.account.domain.type.Role;
import intergration.feed.cert.domain.Cert;
import intergration.feed.common.error.wanted.ErrorCode;
import intergration.feed.common.error.wanted.WantedException;
import intergration.feed.validate.Password;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AccountRequestDto {
    @Getter
    @AllArgsConstructor
    public static class Join {

        @NotBlank
        private String loginId;

        @NotBlank
        private String password;

        @Email
        @NotBlank
        private String email;

        public void encryptPassword(PasswordEncoder bCryptPasswordEncoder) {
            Password.validate(password);
            password = bCryptPasswordEncoder.encode(password);
        }

        public Account toEntity(Role role) {
            return Account.create(loginId,email,password,role);
        }
    }
    @Getter
    @AllArgsConstructor
    public static class ValidateCertCode {
        private String loginId;
        private String certCode;

        public void equalsCode(Cert cert) {
            if(cert.getCode().equals(certCode)) {
                throw new WantedException(ErrorCode.NOT_EQUALS_CERT_CODE);
            }
        }
    }

    @Getter
    @AllArgsConstructor
    public static class Login {
        @NotBlank
        private String loginId;
        @NotBlank
        private String password;
    }
}
