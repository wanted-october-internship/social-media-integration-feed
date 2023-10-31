package intergration.feed.app.history;

import intergration.feed.app.account.annotation.LoginCheck;
import intergration.feed.app.account.dto.AccountResponseDto.LoginInfo;
import intergration.feed.app.history.dto.HistoryRequestDto;
import intergration.feed.app.post.dto.PostResponseDto.Posting;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/history")
public class HistoryController {
    private final HistoryReadService historyReadService;
    @GetMapping
    public List<Posting> getHistories(@LoginCheck LoginInfo loginInfo, HistoryRequestDto.Filter request) {
        return historyReadService.getHistoies(loginInfo.getLoginId(), request.getHistoryType());
    }
}
