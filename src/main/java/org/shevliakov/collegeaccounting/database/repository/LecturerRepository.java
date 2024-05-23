package org.shevliakov.collegeaccounting.database.repository;

import java.util.List;
import org.shevliakov.collegeaccounting.entity.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

  @Query("SELECT l FROM Lecturer l")
  List<Lecturer> getAll();
}
