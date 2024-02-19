package hibuy.server.common.exception_handler;

import hibuy.server.common.exception.FieldException;
import hibuy.server.common.exception.notfound.*;
import hibuy.server.common.response.BaseErrorResponse;
import hibuy.server.common.response.RequestErrorResponse;
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
    public RequestErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("[handle_MethodArgumentNotValidException]", e);
        return new RequestErrorResponse(METHOD_ARGUMENT_NOT_VALID, FieldException.create(e.getBindingResult()));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public BaseErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("[handle_HttpMessageNotReadableException]", e);
        return new BaseErrorResponse(INVALID_JSON);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public BaseErrorResponse handleNotFoundUserException(NotFoundUserException e) {
        log.error("[handle_NotFoundUserException]", e);
        return new BaseErrorResponse(USER_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public BaseErrorResponse handleNotFoundAddressException(NotFoundAddressException e) {
        log.error("[handle_NotFoundAddressException]", e);
        return new BaseErrorResponse(ADDRESS_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public BaseErrorResponse handleNotFoundAddressException(NotFoundBoolTakeException e) {
        log.error("[handle_NotFoundBoolTakeException]", e);
        return new BaseErrorResponse(BOOL_TAKE_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public BaseErrorResponse handleNotFoundDateCountException(NotFoundDateCountException e) {
        log.error("[handle_NotFoundDateCountException]", e);
        return new BaseErrorResponse(DATE_COUNT_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public BaseErrorResponse handleUserProductNotFound(NotFoundUserProductException e) {
        log.error("[handle_NotFoundUserProductException]", e);
        return new BaseErrorResponse(USER_PRODUCT_NOT_FOUND);
    }

}
