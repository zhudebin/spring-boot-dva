package com.blue.bird.commons;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Created by jim on 2017/9/27.
 */
@Aspect
@Component
@Slf4j
public class LoggerAop {

    @Pointcut(value = "@annotation(com.blue.bird.annotation.LogAnnotation)")
    private void doPointcut() {
        log.info("doPointcut Aspect");
    }


    /**
     * 前置通知（Before）：在目标方法被调用之前调用通知功能
     *
     * @param joinPoint
     */
    @Before(value = "doPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("doBefore joinPoint:{}", joinPoint);
        Object[] params = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String target = joinPoint.getSignature().getDeclaringTypeName();
//        LoggerBO.newLoginBO().setTarget(target).setMethod(methodSignature.getMethod()).setParams(params).log();
    }


    /**
     * 环绕通知（Around）：通知包裹了被通知的方法，在被通知的方法调 用之前和调用之后执行自定义的行为
     *
     * @param proceedingJoinPoint
     */
    @Around(value = "doPointcut()")
    public void doAround(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("doAround proceedingJoinPoint:{}", proceedingJoinPoint);
        Object[] params = proceedingJoinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String target = methodSignature.getDeclaringTypeName();
//        LoggerBO.newLoginBO().setTarget(target).setMethod(methodSignature.getMethod()).setParams(params).log();
    }

    /**
     * 后置通知（After）：在目标方法完成之后调用通知，此时不会关心方 法的输出是什么
     *
     * @param joinPoint
     */
    @After(value = "doPointcut()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("doAfter joinPoint:{}", joinPoint);
        Object[] params = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String target = joinPoint.getSignature().getDeclaringTypeName();
//        LoggerBO.newLoginBO().setTarget(target).setMethod(methodSignature.getMethod()).setParams(params).log();
    }

    /**
     * 返回通知（After-returning）：在目标方法成功执行之后调用通知
     *
     * @param joinPoint
     * @param retValue
     */
    @AfterReturning(value = "doPointcut()", returning = "retValue")
    public void doAfterReturning(JoinPoint joinPoint, Object retValue) {
        log.info("doAfterReturning joinPoint:{},{}", joinPoint, retValue);
        Object[] params = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String target = joinPoint.getSignature().getDeclaringTypeName();
//        LoggerBO.newLoginBO().setTarget(target).setMethod(methodSignature.getMethod()).setParams(params).log();
    }

    /**
     * 异常通知（After-throwing）：在目标方法抛出异常后调用通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "doPointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        log.info("doAfterThrowing joinPoint:{}", e);
        Object[] params = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String target = joinPoint.getSignature().getDeclaringTypeName();
//        LoggerBO.newLoginBO().setTarget(target).setMethod(methodSignature.getMethod()).setParams(params).log();
    }
}
