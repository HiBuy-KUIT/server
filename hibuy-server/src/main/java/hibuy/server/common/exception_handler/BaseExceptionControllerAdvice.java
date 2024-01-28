package hibuy.server.common.exception_handler;

import hibuy.server.common.response.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static hibuy.server.common.response.status.BaseExceptionResponseStatus.*;

@Slf4j
@RestControllerAdvice
public class BaseExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public BaseErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("[handle_MethodArgumentNotValidException]", e);
        return new BaseErrorResponse(METHOD_ARGUMENT_NOT_VALID, getMethodArgumentNotValidErrorMessage(e));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public BaseErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("[handle_HttpMessageNotReadableException]", e);
        return new BaseErrorResponse(JSON_PARSING_ERROR);
    }

    private String getMethodArgumentNotValidErrorMessage(MethodArgumentNotValidException e) {
        return e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();
    }

}
