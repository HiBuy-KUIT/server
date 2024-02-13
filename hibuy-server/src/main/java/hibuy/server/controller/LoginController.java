package hibuy.server.controller;

import hibuy.server.common.response.BaseResponse;
import hibuy.server.dto.oauth2.LoginResponse;
import hibuy.server.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final KakaoService kakaoService;

    @Value("${kakao.oauth2.client_id}") private String clientId;
    @Value("${kakao.oauth2.redirect_uri}") private String redirectUri;

    @GetMapping("/oauth2/code/kakao")
    public ResponseEntity<LoginResponse> requestKakaoLogin(@RequestParam String code) {

        log.debug("[LoginController.requestLogin]");

        String accessToken = kakaoService.getAccessToken(code);
        LoginResponse userInfo = kakaoService.getUserInfo(accessToken);

        System.out.println("userInfo.getName() = " + userInfo.getName());
        System.out.println("userInfo.getEmail() = " + userInfo.getEmail());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/result"));

        return new ResponseEntity<>(userInfo, headers, HttpStatus.MOVED_PERMANENTLY);
    }

}
