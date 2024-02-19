package hibuy.server.common.exception.notfound;

import hibuy.server.common.exception.DatabaseException;
import lombok.Getter;

import static hibuy.server.common.response.status.BaseExceptionResponseStatus.*;

@Getter
public class NotFoundAddressException extends DatabaseException {

    public NotFoundAddressException() {
        super(ADDRESS_NOT_FOUND);
    }
}
