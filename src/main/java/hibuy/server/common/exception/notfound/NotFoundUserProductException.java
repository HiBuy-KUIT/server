package hibuy.server.common.exception.notfound;

import lombok.Getter;

@Getter
public class NotFoundUserProductException extends NotFoundException{

    public NotFoundUserProductException() {
        super("유저 제품을 찾을 수 없습니다.", 1005);
    }
}
