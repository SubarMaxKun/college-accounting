package org.shevliakov.collegeaccounting.database.repository;

import java.sql.Date;
import java.util.List;
import org.shevliakov.collegeaccounting.entity.Rank;
import org.shevliakov.collegeaccounting.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

  @Query("SELECT DISTINCT w.birthDate FROM Worker w")
  List<Date> getDistinctBirthDates();

  @Query("SELECT DISTINCT w.rank FROM Worker w")
  List<Rank> getDistinctRanks();

}
