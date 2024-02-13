package hibuy.server.service;

import hibuy.server.domain.User;
import hibuy.server.dto.oauth2.KakaoTokenResponse;
import hibuy.server.dto.oauth2.KakaoUserInfoResponse;
import hibuy.server.dto.oauth2.LoginResponse;
import hibuy.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final UserRepository userRepository;

    @Value("${kakao.oauth2.client_id}") private String clientId;
    @Value("${kakao.oauth2.redirect_uri}") private String redirectUri;
    @Value("${kakao.oauth2.client_secret}") private String clientSecret;

    public String getAccessToken(String code) {

        log.debug("[KakaoService.getAccessToken]");

        WebClient webClient = WebClient.builder().build();
        String requestUrl = "https://kauth.kakao.com/oauth/token";

        KakaoTokenResponse responseBody = webClient.post()
                .uri(requestUrl)
                .header(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8")
                .bodyValue(buildAccessTokenRequestBody(code))
                .retrieve()
                .bodyToMono(KakaoTokenResponse.class)
                .doOnError(error -> {
                    log.error("do on error: " + error);
                    log.error("error code: " + error.getMessage());
                }).block();

        return responseBody.getAccess_token();
    }

    public LoginResponse getUserInfo(String accessToken) {

        log.debug("[KakaoService.getUserInfo]");

        WebClient webClient = WebClient.builder().build();
        String requestUrl = "https://kapi.kakao.com/v2/user/me";

        KakaoUserInfoResponse kakaoUserInfoResponse = webClient.get()
                .uri(requestUrl)
                .headers(httpHeaders -> {
                    httpHeaders.set("Authorization", "Bearer " + accessToken);
                    httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
                })
                .retrieve()
                .bodyToMono(KakaoUserInfoResponse.class)
                .doOnError(error -> {
                    log.error("do on error: " + error);
                })
                .block();

        Optional<User> user = userRepository.findByKakaoUserId(kakaoUserInfoResponse.getId());

        if(user.isEmpty()) {
            userRepository.save(new User(
                    kakaoUserInfoResponse.getId(),
                    kakaoUserInfoResponse.getKakao_account().getName(),
                    kakaoUserInfoResponse.getKakao_account().getEmail(),
                    kakaoUserInfoResponse.getKakao_account().getPhone_number()));
        }

        return new LoginResponse(
                kakaoUserInfoResponse.getId(),
                kakaoUserInfoResponse.getKakao_account().getName(),
                kakaoUserInfoResponse.getKakao_account().getEmail(),
                kakaoUserInfoResponse.getKakao_account().getPhone_number());

    }


    private MultiValueMap<String, String> buildAccessTokenRequestBody(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", URLEncoder.encode(redirectUri, StandardCharsets.UTF_8));
        params.add("code", code);
        return params;
    }
}
