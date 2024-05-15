package org.shevliakov.collegeaccounting.database.repository;

import java.util.List;
import org.shevliakov.collegeaccounting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u")
  List<User> getAll();

  User getByUsername(String username);


}
