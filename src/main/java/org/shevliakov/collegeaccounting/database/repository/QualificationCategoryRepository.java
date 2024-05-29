package org.shevliakov.collegeaccounting.database.repository;

import java.util.List;
import org.shevliakov.collegeaccounting.entity.QualificationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QualificationCategoryRepository extends
    JpaRepository<QualificationCategory, Long> {

  @Query("SELECT qc FROM QualificationCategory qc")
  List<QualificationCategory> getAll();

  QualificationCategory getByName(String name);
}
