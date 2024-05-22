package org.shevliakov.collegeaccounting.database.repository;

import java.util.List;
import org.shevliakov.collegeaccounting.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {

  @Query("SELECT r FROM Rank r")
  List<Rank> getAll();

  Rank getByName(String name);

  boolean existsByName(String name);
}