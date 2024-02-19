package hibuy.server.common.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestHeader {

    CONTENT_TYPE_URL_ENCODED("Content-type", "application/x-www-form-urlencoded;charset=utf-8"),
    AUTHORIZATION("Authorization", "Bearer ");

    private final String headerName;
    private final String headerValue;

}
