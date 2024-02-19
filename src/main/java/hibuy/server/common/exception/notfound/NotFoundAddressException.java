package hibuy.server.common.exception.notfound;

import lombok.Getter;

@Getter
public class NotFoundAddressException extends NotFoundException {

    public NotFoundAddressException() {
        super("존재하지 않는 주소입니다", 1002);
    }
}
