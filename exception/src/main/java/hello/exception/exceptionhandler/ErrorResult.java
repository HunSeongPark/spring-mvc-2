package hello.exception.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Hunseong on 2022/04/04
 */
@Data
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;
}
