package hibuy.server.common.config.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "kakao")
public class KakaoProperties {

    private final Oauth2 oauth2;

    public record Oauth2(String redirectUri, String clientId, String clientSecret) {

    }

}
