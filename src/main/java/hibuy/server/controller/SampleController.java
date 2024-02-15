package hibuy.server.controller;

import hibuy.server.common.response.BaseResponse;
import hibuy.server.dto.sample.GetSampleResponse;
import hibuy.server.dto.sample.PostSampleRequest;
import hibuy.server.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/samples")
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @PostMapping()
    public BaseResponse<String> addSample(@Validated @RequestBody PostSampleRequest postSampleRequest) {

        log.debug("[SampleController.addSample]");

        sampleService.addSample(postSampleRequest);

        return new BaseResponse<>(null);

    }

    @GetMapping()
    public BaseResponse<List<GetSampleResponse>> getSamples() {

        log.debug("[SampleController.getSamples]");

        return new BaseResponse<>(sampleService.getSamples());
    }

}
