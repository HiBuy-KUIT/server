package hibuy.server.controller;

import hibuy.server.common.response.BaseResponse;
import hibuy.server.dto.dateCount.GetDateCountResponse;
import hibuy.server.service.DateCountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("users/{userId}/count")
@RequiredArgsConstructor
public class DateCountController {

    private final DateCountService dateCountService;

    @GetMapping()
    private BaseResponse<GetDateCountResponse> getUserDateCount(@PathVariable Long userId) {
        log.debug("[DateCountController.getDateCount]");
        return new BaseResponse<>(dateCountService.getUserDateCount(userId));
    }

    @PatchMapping()
    private BaseResponse<String> updateUserDateCount(@PathVariable Long userId) {
        log.debug("[DateCountController.patchUserDateCount]");
        dateCountService.updateUserDateCount(userId);
        return new BaseResponse<>(null);
    }
}
