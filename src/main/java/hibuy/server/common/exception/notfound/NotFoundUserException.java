package hibuy.server.common.exception.notfound;

import lombok.Getter;

@Getter
public class NotFoundUserException extends NotFoundException {

    public NotFoundUserException() {
        super("존재하지 않는 회원입니다.", 1001);
    }

}
