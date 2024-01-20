package com.good.fortunecookierest.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.good.fortunecookierest.model.Fortune;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Disabled
public class FortuneRepositoryTestAlteringProdDatabase {

  @Autowired private FortuneRepository fortuneRepository;

  @Test
  public void findDistinctOnAuthorOk() {
    Fortune fortune =
        Fortune.builder()
            .description(
                "In the garden of code, patience is the soil. Bugs, fleeting as clouds, come and go. Debug with a calm mind, and let go.")
            .createdAt(LocalDateTime.now())
            .author("Zen Master CodeSanity")
            .build();
    Fortune fortune2 =
        Fortune.builder()
            .description(
                "Embrace impermanence in refactoring. Code evolves, each change a step on the path. Be present in the process.")
            .createdAt(LocalDateTime.now())
            .author("Coding Sage TranquilByte")
            .build();
    Fortune fortune3 =
        Fortune.builder()
            .description(
                "Seek the Middle Way in coding. Neither over-engineer nor under-engineer; find balance for software wisdom.")
            .createdAt(LocalDateTime.now())
            .author("Coding Sage TranquilByte")
            .build();
    Fortune fortune4 =
        Fortune.builder()
            .description(
                "Practice mindfulness in algorithms. Let each line be a meditation, a step toward clarity. Solve with focus.")
            .createdAt(LocalDateTime.now())
            .author("Zen Master CodeSanity")
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

  @Test
  public void clearFortunesAndFindDistinctOnAuthorOk() {
    fortuneRepository.deleteAll();

    Fortune fortune =
        Fortune.builder()
            .description(
                "In the garden of code, patience is the soil. Bugs, fleeting as clouds, come and go. Debug with a calm mind, and let go.")
            .createdAt(LocalDateTime.now())
            .author("Zen Master CodeSanity")
            .build();
    Fortune fortune2 =
        Fortune.builder()
            .description(
                "Embrace impermanence in refactoring. Code evolves, each change a step on the path. Be present in the process.")
            .createdAt(LocalDateTime.now())
            .author("Coding Sage TranquilByte")
            .build();
    Fortune fortune3 =
        Fortune.builder()
            .description(
                "Seek the Middle Way in coding. Neither over-engineer nor under-engineer; find balance for software wisdom.")
            .createdAt(LocalDateTime.now())
            .author("Coding Sage TranquilByte")
            .build();
    Fortune fortune4 =
        Fortune.builder()
            .description(
                "Practice mindfulness in algorithms. Let each line be a meditation, a step toward clarity. Solve with focus.")
            .createdAt(LocalDateTime.now())
            .author("Zen Master CodeSanity")
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
}
