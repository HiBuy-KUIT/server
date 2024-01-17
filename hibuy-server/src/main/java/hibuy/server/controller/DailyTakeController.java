package hibuy.server.controller;

import hibuy.server.common.response.BaseResponse;
import hibuy.server.dto.dailyTake.GetMonthlyTakeResponse;
import hibuy.server.dto.dailyTake.PostDailyTakeRequest;
import hibuy.server.service.DailyTakeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/daily-take")
@RequiredArgsConstructor
public class DailyTakeController {

    private final DailyTakeService dailyTakeService;

    @GetMapping()
    private BaseResponse<GetMonthlyTakeResponse> getMonthlyTake(@PathVariable Long userId,
                                                                @RequestParam String year,
                                                                @RequestParam String month) {
        log.debug("[DailyTakeController.getMonthlyTake]");
        return new BaseResponse<>(dailyTakeService.getMonthlyTake(userId, year, month));
    }

    @PostMapping()
    private BaseResponse<String> addDailyTake(@PathVariable Long userId, @RequestBody PostDailyTakeRequest postDailyTakeRequest) {
        log.debug("[DailyTakeController.addDailyTake]");
        dailyTakeService.addDailyTake(userId, postDailyTakeRequest.getDate());
        return new BaseResponse<>(null);
    }
}
