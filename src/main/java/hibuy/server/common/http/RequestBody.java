package hibuy.server.common.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestBody {

    /**
     * KAKAO OAUTH2 ACCESS TOKEN 발급 요청 본문
     */
    GRANT_TYPE("grant_type"),

    CLIENT_ID("client_id"),

    CLIENT_SECRET("client_secret"),

    REDIRECT_URI("redirect_uri"),

    CODE("code"),

    AUTHORIZATION_CODE("authorization_code");

    private final String name;
}
