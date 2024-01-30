package hibuy.server.common.exception;

import hibuy.server.common.response.status.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DatabaseException extends RuntimeException{

    private final ResponseStatus exceptionStatus;
}
