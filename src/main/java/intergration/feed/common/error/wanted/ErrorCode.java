package intergration.feed.common.error.wanted;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    NOT_FOUND_ACCOUNT(HttpStatus.NOT_FOUND,"회원이 존재하지 않습니다."),
    NOT_FOUND_CERT_CODE(HttpStatus.NOT_FOUND,"인증코드가 존재하지 않습니다."),
    NOT_FOUND_POST(HttpStatus.NOT_FOUND,"게시물이 존재하지 않습니다."),

    NOT_CERT_ACCOUNT(HttpStatus.BAD_REQUEST,"인증이 미완료된 회원입니다."),
    NOT_EQUALS_LOGIN_INFO(HttpStatus.BAD_REQUEST, "아이디 또는 비밀번호가 틀렸습니다."),
    NOT_EQUALS_CERT_CODE(HttpStatus.BAD_REQUEST, "인증코드가 일치하지 않습니다."),
    MUST_NOT_BE_ALL_DIGITS(HttpStatus.BAD_REQUEST, "비밀번호는 숫자로만 이루어질 수 없습니다."),
    NO_REPEAT_CHARACTERS_AND_MULTIPLE_CRITERIA(HttpStatus.BAD_REQUEST,"비밀번호는 숫자, 문자, 특수 문자 중 최소 2가지를 포함하며, 3회 이상 연속된 문자는 사용할 수 없습니다."),

    ALREADY_LOGIN_ID(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다.");

    private final HttpStatus status;
    private final String message;

    public int getStatusCode() {
        return status.value();
    }
}
