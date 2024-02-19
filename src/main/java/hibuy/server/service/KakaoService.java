package hibuy.server.service;

import hibuy.server.common.URL;
import hibuy.server.domain.DateCount;
import hibuy.server.domain.User;
import hibuy.server.dto.oauth2.KakaoTokenResponse;
import hibuy.server.dto.oauth2.KakaoUserInfoResponse;
import hibuy.server.dto.oauth2.LoginResponse;
import hibuy.server.repository.DateCountRepository;
import hibuy.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

    private final UserRepository userRepository;
    private final DateCountRepository dateCountRepository;

    @Value("${kakao.oauth2.client_id}") private String clientId;
    @Value("${kakao.oauth2.redirect_uri}") private String redirectUri;
    @Value("${kakao.oauth2.client_secret}") private String clientSecret;

    public String getAccessToken(String code) {

        log.debug("[KakaoService.getAccessToken]");

        WebClient webClient = WebClient.builder().build();

        KakaoTokenResponse responseBody = webClient.post()
                .uri(URL.KAKAO_TOKEN_URL.getUrl())
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .bodyValue(buildAccessTokenRequestBody(code))
                .retrieve()
                .bodyToMono(KakaoTokenResponse.class)
                .doOnError(error -> {
                    log.error("do on error: " + error);
                })
                .block();

        return responseBody.getAccess_token();
    }

    public LoginResponse getUserInfo(String accessToken) {

        log.debug("[KakaoService.getUserInfo]");

        WebClient webClient = WebClient.builder().build();

        KakaoUserInfoResponse kakaoUserInfoResponse = webClient.get()
                .uri(URL.KAKAO_USER_INFO_URL.getUrl())
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
            User savedUser = userRepository.save(new User(
                    kakaoUserInfoResponse.getId(),
                    kakaoUserInfoResponse.getKakao_account().getName(),
                    kakaoUserInfoResponse.getKakao_account().getEmail(),
                    kakaoUserInfoResponse.getKakao_account().getPhone_number()));

            dateCountRepository.save(new DateCount(savedUser));
        }

        return new LoginResponse(
                kakaoUserInfoResponse.getKakao_account().getName(),
                kakaoUserInfoResponse.getKakao_account().getEmail(),
                kakaoUserInfoResponse.getKakao_account().getPhone_number());

    }


    private MultiValueMap<String, String> buildAccessTokenRequestBody(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        return params;
    }
}
