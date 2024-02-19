package hibuy.server.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum URL {

    KAKAO_TOKEN_URL("https://kauth.kakao.com/oauth/token"),

    KAKAO_USER_INFO_URL("https://kapi.kakao.com/v2/user/me");

    private final String url;

}
