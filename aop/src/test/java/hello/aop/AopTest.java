package hello.aop;

import hello.aop.order.OrderRepository;
import hello.aop.order.OrderService;
import hello.aop.order.aop.AspectV1;
import hello.aop.order.aop.AspectV2;
import hello.aop.order.aop.AspectV3;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertThrows;

//@Import(AspectV1.class)
//@Import(AspectV2.class)
@Import(AspectV3.class)
@SpringBootTest
public class AopTest {

  @Autowired
  OrderService orderService;

  @Autowired
  OrderRepository orderRepository;

  @Test
  void aopInfo() {
    System.out.println("isAopProxy, orderService=" + AopUtils.isAopProxy(orderService));
    System.out.println("isAopProxy, orderRepository=" + AopUtils.isAopProxy(orderRepository));
  }

  @Test
  void success() {
    orderService.orderItem("itemA");
  }

  @Test
  void exception() {
    assertThrows(IllegalStateException.class, () -> orderService.orderItem("ex"));
  }
}
