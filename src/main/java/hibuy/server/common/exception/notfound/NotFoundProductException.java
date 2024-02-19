package hibuy.server.common.exception.notfound;

import lombok.Getter;

@Getter
public class NotFoundProductException extends NotFoundException{

    public NotFoundProductException() {
        super("존재하지 않는 제품입니다.", 1006);
    }
}
