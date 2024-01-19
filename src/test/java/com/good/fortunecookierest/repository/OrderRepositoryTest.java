package com.good.fortunecookierest.repository;

import com.good.fortunecookierest.model.Fortune;
import com.good.fortunecookierest.model.Order;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(
    classes = {
      FortuneRepository.class,
      OrderRepository.class,
      OrderRepositoryTest.SpringConfig.class
    })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Disabled
public class OrderRepositoryTest {

  @Autowired private FortuneRepository fortuneRepository;
  @Autowired private OrderRepository orderRepository;

  @Test
  public void saveOrderOk() {
    Fortune fortune = Fortune.builder().description("test test").author("Author Doe").build();
    fortuneRepository.save(fortune);

    Order order = Order.builder().fortune(fortune).status(Order.Status.RECEIVED).build();
    orderRepository.save(order);
  }

  @Test
  @Commit // This should not be here!
  public void deleteAllOk() {
    orderRepository.deleteAll();
  }

  @Configuration
  @EntityScan("com.good.fortunecookierest.model")
  @EnableAutoConfiguration
  public static class SpringConfig {}
}
