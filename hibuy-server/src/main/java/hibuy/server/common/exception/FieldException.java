package hibuy.server.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class FieldException {

    private String field;
    private String value;
    private String reason;

    public static List<FieldException> create(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(error -> new FieldException(
                        error.getField(),
                        (error.getRejectedValue() == null) ? null : error.getRejectedValue().toString(),
                        error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

}
