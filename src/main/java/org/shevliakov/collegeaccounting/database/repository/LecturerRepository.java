package org.shevliakov.collegeaccounting.database.repository;

import java.util.List;
import org.shevliakov.collegeaccounting.entity.Lecturer;
import org.shevliakov.collegeaccounting.entity.PedagogicalTitle;
import org.shevliakov.collegeaccounting.entity.QualificationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

  @Query("SELECT l FROM Lecturer l")
  List<Lecturer> getAll();

  @Query("SELECT DISTINCT l.category FROM Lecturer l")
  List<QualificationCategory> getDistinctQualificationCategories();

  @Query("SELECT DISTINCT l.title FROM Lecturer l")
  List<PedagogicalTitle> getDistinctPedagogicalTitles();

  @Query("SELECT DISTINCT l.nextCertification FROM Lecturer l")
  List<Integer> getDistinctNextCertification();
}
