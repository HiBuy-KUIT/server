package hibuy.server.common.exception.notfound;

import hibuy.server.common.exception.DatabaseException;
import lombok.Getter;

import static hibuy.server.common.response.status.BaseExceptionResponseStatus.*;

@Getter
public class NotFoundBoolTakeException extends DatabaseException {

    public NotFoundBoolTakeException() {
        super(BOOL_TAKE_NOT_FOUND);
    }
}
