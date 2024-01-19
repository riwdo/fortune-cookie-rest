package com.good.fortunecookierest.repository;

import com.good.fortunecookierest.model.Fortune;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FortuneRepository extends JpaRepository<Fortune, Long> {

  @Query(nativeQuery = true, value = "SELECT DISTINCT ON(author) * FROM fortune")
  List<Fortune> findDistinctOnAuthor();
}
