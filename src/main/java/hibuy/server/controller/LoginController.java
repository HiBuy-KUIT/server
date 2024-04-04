package hibuy.server.controller;

import hibuy.server.common.response.BaseResponse;
import hibuy.server.dto.oauth2.LoginResponse;
import hibuy.server.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final KakaoService kakaoService;

    @GetMapping("/oauth2/code/kakao")
    public BaseResponse<LoginResponse> requestKakaoLogin(@RequestParam String code) {

        log.debug("[LoginController.requestLogin]");

        String accessToken = kakaoService.getAccessToken(code);

        return new BaseResponse<>(kakaoService.getUserInfo(accessToken));

    }

}
