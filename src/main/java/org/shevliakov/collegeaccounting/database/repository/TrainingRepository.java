package org.shevliakov.collegeaccounting.database.repository;

import org.shevliakov.collegeaccounting.entity.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {

  Training getByName(String name);
}
