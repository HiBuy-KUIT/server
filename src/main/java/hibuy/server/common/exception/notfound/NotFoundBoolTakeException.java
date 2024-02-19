package hibuy.server.common.exception.notfound;

import lombok.Getter;

@Getter
public class NotFoundBoolTakeException extends NotFoundException{

    public NotFoundBoolTakeException() {
        super("해당 제품별 섭취여부가 존재하지 않습니다.", 1003);
    }
}
