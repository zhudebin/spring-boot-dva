package com.blue.bird.commons;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.blue.bird.commons.Constants.LOGGER_RETVALUE;

/**
 * Created by jim on 2017/9/29.
 */
@Aspect
@Component
@Slf4j
public class RestControllerLoggerAop {
    @Pointcut(value = "@within(org.springframework.web.bind.annotation.RestController) || @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    private void doPointcut() {
        //
    }

    @Before(value = "doPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        //
    }

    @AfterReturning(value = "doPointcut()", returning = "retValue")
    public void doAfterReturning(JoinPoint joinPoint, Object retValue) {
        Object value = retValue;
        if (retValue instanceof ModelAndView) {
            value = ((ModelAndView) retValue).getModel();
        }
        httpServletRequest().setAttribute(LOGGER_RETVALUE, value);
    }

    @AfterThrowing(value = "doPointcut()", throwing = "exception")
    public void doAfterReturning(JoinPoint joinPoint, Exception exception) {
        httpServletRequest().setAttribute(LOGGER_RETVALUE, LoginUtils.logException(exception));
    }

    private HttpServletRequest httpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        return servletRequestAttributes.getRequest();
    }
}
