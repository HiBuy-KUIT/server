package hibuy.server.controller;

import hibuy.server.common.response.BaseResponse;
import hibuy.server.dto.booltake.PatchBoolTakeRequest;
import hibuy.server.dto.booltake.PatchBoolTakeResponse;
import hibuy.server.service.BoolTakeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/is-take")
public class BoolTakeController {

    private final BoolTakeService boolTakeService;

    @PatchMapping("")
    public BaseResponse<PatchBoolTakeResponse> updateBoolTake(@RequestBody PatchBoolTakeRequest request) {
        log.debug("[BoolTakeController.updateBoolTake");

        return new BaseResponse<>(boolTakeService.updateBoolTake(request));
    }
}
