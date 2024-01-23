package com.good.fortunecookierest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.good.fortunecookierest.model.Fortune;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test-h2")
@Disabled
public class FortuneRepositoryH2Test {

  @Autowired private FortuneRepository fortuneRepository;

  @Test
  public void saveOk() {
    Fortune fortune =
        Fortune.builder()
            .description(
                "In the garden of code, patience is the soil. Bugs, fleeting as clouds, come and go. Debug with a calm mind, and let go.")
            .createdAt(LocalDateTime.now())
            .author("Zen Master CodeSanity")
            .build();

    fortuneRepository.save(fortune);

    List<Fortune> fortuneList = fortuneRepository.findAll();
    assertEquals(1, fortuneList.size());
  }

  @Test
  public void clearFortunesAndSaveOk() {
    fortuneRepository.deleteAll();

    Fortune fortune =
        Fortune.builder()
            .description(
                "In the garden of code, patience is the soil. Bugs, fleeting as clouds, come and go. Debug with a calm mind, and let go.")
            .createdAt(LocalDateTime.now())
            .author("Zen Master CodeSanity")
            .build();

    fortuneRepository.save(fortune);

    List<Fortune> fortuneList = fortuneRepository.findAll();
    assertEquals(1, fortuneList.size());
  }
}
