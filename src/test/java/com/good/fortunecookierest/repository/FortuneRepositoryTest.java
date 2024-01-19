package com.good.fortunecookierest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.good.fortunecookierest.model.Fortune;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {FortuneRepository.class, FortuneRepositoryTest.SpringConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Disabled
public class FortuneRepositoryTest {

  @Autowired private FortuneRepository fortuneRepository;

  @Test
  public void findDistinctOnAuthorOk() {
    fortuneRepository.deleteAll();

    Fortune fortune =
        Fortune.builder()
            .description("test")
            .createdAt(LocalDateTime.now())
            .author("Author 1")
            .build();
    Fortune fortune2 =
        Fortune.builder()
            .description("test 2")
            .createdAt(LocalDateTime.now())
            .author("Author 1")
            .build();
    Fortune fortune3 =
        Fortune.builder()
            .description("test 3")
            .createdAt(LocalDateTime.now())
            .author("Author 2")
            .build();
    Fortune fortune4 =
        Fortune.builder()
            .description("test 4")
            .createdAt(LocalDateTime.now())
            .author("Author 2")
            .build();

    fortuneRepository.save(fortune);
    fortuneRepository.save(fortune2);
    fortuneRepository.save(fortune3);
    fortuneRepository.save(fortune4);

    List<Fortune> fortuneList = fortuneRepository.findAll();
    assertEquals(4, fortuneList.size());

    List<Fortune> distinctFortuneList = fortuneRepository.findDistinctOnAuthor();
    assertEquals(2, distinctFortuneList.size());
  }

  @Configuration
  @EntityScan("com.good.fortunecookierest.model")
  @EnableAutoConfiguration
  public static class SpringConfig {}
}
