package hibuy.server.common.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import hibuy.server.common.exception.FieldException;
import hibuy.server.common.response.status.ResponseStatus;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@JsonPropertyOrder({"code", "status", "message", "timestamp", "exceptions"})
public class RequestErrorResponse implements ResponseStatus {

    private final int code;
    private final int status;
    private final String message;
    private final LocalDateTime timestamp;
    private final List<FieldException> exceptions;

    public RequestErrorResponse(ResponseStatus status, List<FieldException> exceptions) {
        this.code = status.getCode();
        this.status = status.getStatus();
        this.message = status.getMessage();
        this.timestamp = LocalDateTime.now();
        this.exceptions = (exceptions == null) ? new ArrayList<>() : exceptions;
    }

}