package intergration.feed.app.history.dto;

import intergration.feed.app.history.dto.type.HistoryType;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class HistoryRequestDto {

    @Getter
    @AllArgsConstructor
    public static class Filter {
        private HistoryType historyType;
    }

}
