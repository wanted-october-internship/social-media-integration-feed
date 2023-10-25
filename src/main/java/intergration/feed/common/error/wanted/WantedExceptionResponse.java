package intergration.feed.common.error.wanted;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : intergration.feed.common.error
 * fileName       : WantedExceptionResponse
 * author         : jhw1015
 * date           : 2023/10/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/10/25        jhw1015               최초 생성
 */
@Getter
@RequiredArgsConstructor
@Builder
public class WantedExceptionResponse {
    private final LocalDateTime time;
    private final int statusCode;
    private final String message;

    public static WantedExceptionResponse responseWantedException(
        WantedException wantedException) {
        return WantedExceptionResponse.builder()
            .statusCode(wantedException.getStatusCode())
            .time(LocalDateTime.now())
            .message(wantedException.getMessage())
            .build();
    }

}
