package hibuy.server.common.exception.notfound;

import hibuy.server.common.exception.DatabaseException;
import lombok.Getter;

import static hibuy.server.common.response.status.BaseExceptionResponseStatus.*;

@Getter
public class NotFoundProductException extends DatabaseException {

    public NotFoundProductException() {
        super(PRODUCT_NOT_FOUND);
    }
}
