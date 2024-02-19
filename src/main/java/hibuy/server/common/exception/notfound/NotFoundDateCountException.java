package hibuy.server.common.exception.notfound;

import lombok.Getter;

@Getter
public class NotFoundDateCountException extends NotFoundException{

    public NotFoundDateCountException() {
        super("해당 누적일수가 존재하지 않습니다.", 1004);
    }
}
