package org.shevliakov.collegeaccounting.database.repository;

import java.sql.Date;
import java.util.List;
import org.shevliakov.collegeaccounting.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {

  @Query("SELECT s FROM Student s")
  List<Student> getAll();

  @Query("SELECT DISTINCT s.birthDate FROM Student s")
  List<Date> getDistinctBirthDates();
}
