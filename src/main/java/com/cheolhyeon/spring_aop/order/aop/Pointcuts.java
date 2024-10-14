package com.cheolhyeon.spring_aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    //클래스 이름 패턴이 *Service인 곳에다가 AOP 적용
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}

    @Pointcut("execution(* com.cheolhyeon.spring_aop.order..*(..))")
    public void allOrder() {}

    @Pointcut("allOrder() && allService()")
    public void allOrderAndService() {}

}
