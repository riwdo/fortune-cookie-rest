package com.good.fortunecookierest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    Fortune fortune =
        Fortune.builder()
            .description(
                "In the garden of code, patience is the soil. Bugs, fleeting as clouds, come and go. Debug with a calm mind, and let go.")
            .author("Zen Master CodeSanity")
            .build();
    fortuneRepository.save(fortune);

    Order order = Order.builder().fortune(fortune).status(Order.Status.RECEIVED).build();
    orderRepository.save(order);

    var orders = orderRepository.findAll();
    assertEquals(1, orders.size());
  }

  @Test
  @Commit // This should not be here!
  public void deleteAllOk() {
    orderRepository.deleteAll();

    var orders = orderRepository.findAll();
    assertEquals(0, orders.size());
  }

  @Configuration
  @EntityScan("com.good.fortunecookierest.model")
  @EnableAutoConfiguration
  public static class SpringConfig {}
}
