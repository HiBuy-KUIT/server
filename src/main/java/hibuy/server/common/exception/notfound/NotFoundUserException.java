package hibuy.server.common.exception.notfound;

import hibuy.server.common.exception.DatabaseException;
import lombok.Getter;

import static hibuy.server.common.response.status.BaseExceptionResponseStatus.*;

@Getter
public class NotFoundUserException extends DatabaseException {

    public NotFoundUserException() {
        super(USER_NOT_FOUND);
    }

}
