package hibuy.server.common.exception.notfound;

import hibuy.server.common.exception.DatabaseException;
import lombok.Getter;

import static hibuy.server.common.response.status.BaseExceptionResponseStatus.*;

@Getter
public class NotFoundDateCountException extends DatabaseException {

    public NotFoundDateCountException() {
        super(DATE_COUNT_NOT_FOUND);
    }
}
