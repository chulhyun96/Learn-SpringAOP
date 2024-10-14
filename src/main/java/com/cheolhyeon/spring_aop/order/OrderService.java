package com.cheolhyeon.spring_aop.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void orderItem(String itemId) {
        log.info("[Order Service] 실행");
        orderRepository.save(itemId);
    }
}
