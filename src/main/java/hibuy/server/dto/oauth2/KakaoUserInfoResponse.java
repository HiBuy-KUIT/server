package hibuy.server.dto.oauth2;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KakaoUserInfoResponse {

    private Long id;
    private KakaoAccount kakao_account;

    @Getter
    public static class KakaoAccount{
        private String name;
        private String email;
        private String phone_number;
    }

    public String getUsername() {
        return this.getKakao_account().getName();
    }

    public String getUserEmail() {
        return this.getKakao_account().getEmail();
    }

    public String getUserPhoneNumber() {
        return this.getKakao_account().getPhone_number();
    }

}
