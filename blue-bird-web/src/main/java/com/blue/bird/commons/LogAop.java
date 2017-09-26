package com.blue.bird.commons;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by jim on 2017/9/27.
 */
@Aspect
@Component
@Slf4j
public class LogAop {

    @Pointcut(value = "@annotation(com.blue.bird.annotation.LogAnnotation)")
    private void doPointcut() {
        log.info("Aspect");
    }


    @Before(value = "doPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("Aspect");
    }


    @After(value = "doPointcut()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("Aspect");
    }

    @AfterReturning(value = "doPointcut()", returning = "params")
    public void doAfterReturning(JoinPoint joinPoint, Object params) {
        log.info("Aspect");
    }

    @Around(value = "doPointcut()")
    public void doAround(JoinPoint joinPoint) {
        log.info("Aspect");
    }
}
