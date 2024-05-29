package org.shevliakov.collegeaccounting.database.repository;

import java.util.List;
import org.shevliakov.collegeaccounting.entity.PedagogicalTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedagogicalTitleRepository extends JpaRepository<PedagogicalTitle, Long> {

  @Query("SELECT pt FROM PedagogicalTitle pt")
  List<PedagogicalTitle> getAll();

  PedagogicalTitle getByName(String name);
}
