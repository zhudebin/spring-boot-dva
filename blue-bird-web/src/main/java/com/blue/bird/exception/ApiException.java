package com.blue.bird.exception;

import com.blue.bird.enums.ViewCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by jim on 2017/9/27.
 */
public class ApiException extends RuntimeException{

    @Setter
    @Getter
    private ViewCodeEnum viewCodeEnum;

    public ApiException(ViewCodeEnum viewCodeEnum) {
        super(viewCodeEnum.getCode().concat("-").concat(viewCodeEnum.getInfo()));
        this.viewCodeEnum = viewCodeEnum;
    }

    public ApiException(String message, ViewCodeEnum viewCodeEnum) {
        super(message);
        this.viewCodeEnum = viewCodeEnum;
    }

    public ApiException(String message, Throwable cause, ViewCodeEnum viewCodeEnum) {
        super(message, cause);
        this.viewCodeEnum = viewCodeEnum;
    }

    public ApiException(Throwable cause, ViewCodeEnum viewCodeEnum) {
        super(cause);
        this.viewCodeEnum = viewCodeEnum;
    }

    public ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ViewCodeEnum viewCodeEnum) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.viewCodeEnum = viewCodeEnum;
    }
}
