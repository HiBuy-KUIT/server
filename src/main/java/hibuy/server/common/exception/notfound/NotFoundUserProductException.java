package hibuy.server.common.exception.notfound;

import hibuy.server.common.exception.DatabaseException;
import hibuy.server.common.response.status.BaseExceptionResponseStatus;
import lombok.Getter;

@Getter
public class NotFoundUserProductException extends DatabaseException {

    public NotFoundUserProductException() {
        super(BaseExceptionResponseStatus.USER_PRODUCT_NOT_FOUND);
    }
}
