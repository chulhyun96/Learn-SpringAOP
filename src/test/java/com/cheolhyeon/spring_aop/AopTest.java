package com.cheolhyeon.spring_aop;

import com.cheolhyeon.spring_aop.order.OrderRepository;
import com.cheolhyeon.spring_aop.order.OrderService;
import com.cheolhyeon.spring_aop.order.aop.AspectV3;
import com.cheolhyeon.spring_aop.order.aop.AspectV4;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

//@Import(AspectV1.class)
//@Import(AspectV2.class)
//@Import(AspectV3.class)
@Import({AspectV4.LogAspect.class, AspectV4.TxAspect.class})
@Slf4j
@SpringBootTest
public class AopTest {
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    @DisplayName("aopInfo")
    void aopInfo() {
        log.info("isAopProxy, OrderService = {}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, OrderRepository = {}", AopUtils.isAopProxy(orderRepository));
    }
    @Test
    @DisplayName("success")
    void success() {
        orderService.orderItem("itemA");
    }
    @Test
    @DisplayName("ex")
    void ex() {
        Assertions.assertThatThrownBy(() -> orderService.orderItem("ex"))
                .isInstanceOf(IllegalStateException.class);
    }
}
