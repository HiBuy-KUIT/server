package hibuy.server.dto.oauth2;

import hibuy.server.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoUserInfoResponse {

    private Long id;
    private KakaoAccount kakao_account;

    @Getter
    public static class KakaoAccount{
        private String name;
        private String email;
        private String phone_number;
    }

    public User toEntity() {
        return User.builder()
                .kakaoUserId(id)
                .name(kakao_account.name)
                .email(kakao_account.email)
                .phoneNumber(kakao_account.phone_number)
                .build();
    }

}
