package com.cheolhyeon.spring_aop.order.aop;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

@Slf4j
public class AspectV4 {

    @Aspect
    @Order(2)
    public static class LogAspect {
        @Around("com.cheolhyeon.spring_aop.order.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[log] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class TxAspect {
        @Around("com.cheolhyeon.spring_aop.order.aop.Pointcuts.allOrderAndService()")
        public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[트랜잭션 시작] {}", joinPoint.getSignature());
                Object result = joinPoint.proceed();

                Object[] args = joinPoint.getArgs();
                System.out.println("args = " + Arrays.toString(args));

                Object aThis = joinPoint.getThis();
                System.out.println("aThis = " + aThis);

                Object target = joinPoint.getTarget();
                System.out.println("target.toString() = " + target.toString());

                String string = joinPoint.toString();
                System.out.println("string = " + string);
                log.info("[트랜잭션 끝] {}", joinPoint.getSignature());
                //추가
                return result;
            } catch (Exception e) {
                log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
                throw e;
            } finally {
                log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
            }
        }
    }
}
