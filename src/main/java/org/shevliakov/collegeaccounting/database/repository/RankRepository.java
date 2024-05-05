package org.shevliakov.collegeaccounting.database.repository;

import org.shevliakov.collegeaccounting.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {

  Rank getByName(String name);

  boolean existsByName(String name);
}