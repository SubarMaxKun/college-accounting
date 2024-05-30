package org.shevliakov.collegeaccounting.database.repository;

import java.sql.Date;
import java.util.List;
import org.shevliakov.collegeaccounting.entity.Employee;
import org.shevliakov.collegeaccounting.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  @Query("SELECT DISTINCT emp.birthDate FROM Employee emp")
  List<Date> getDistinctBirthDates();

  @Query("SELECT DISTINCT emp.rank FROM Employee emp")
  List<Rank> getDistinctRanks();

}
