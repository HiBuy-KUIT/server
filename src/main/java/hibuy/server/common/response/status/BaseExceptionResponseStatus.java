package hibuy.server.common.response.status;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BaseExceptionResponseStatus implements ResponseStatus {

    /**
     * 1000: 요청 성공 (OK)
     */
    SUCCESS(1000, HttpStatus.OK.value(), "요청에 성공하였습니다."),
    METHOD_ARGUMENT_NOT_VALID(2000, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 입력입니다."),
    INVALID_JSON(2001, HttpStatus.BAD_REQUEST.value(), "JSON 형식이 잘못되었습니다."),
    USER_NOT_FOUND(3001, HttpStatus.NOT_FOUND.value(),"존재하지 않는 회원입니다."),
    ADDRESS_NOT_FOUND(3002, HttpStatus.NOT_FOUND.value(),"존재하지 않는 주소입니다."),
    BOOL_TAKE_NOT_FOUND(3003, HttpStatus.NOT_FOUND.value(), "해당 제품별 섭취여부가 존재하지 않습니다."),
    DATE_COUNT_NOT_FOUND(3004, HttpStatus.NOT_FOUND.value(),"해당 누적일수가 존재하지 않습니다."),
    USER_PRODUCT_NOT_FOUND(3005, HttpStatus.NOT_FOUND.value(),"유저 제품을 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND(3006, HttpStatus.NOT_FOUND.value(),"해당 제품을 찾을 수 없습니다.");

    private final int code;
    private final int status;
    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}