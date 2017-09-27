package com.blue.bird.commons;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Created by jim on 2017/9/28.
 */
@Slf4j
public class LoginUtils {

    public static String logException(Exception excetion) {
        return ExceptionUtils.getRootCauseMessage(excetion);
    }

    private LoginUtils() {
        //
    }
}
