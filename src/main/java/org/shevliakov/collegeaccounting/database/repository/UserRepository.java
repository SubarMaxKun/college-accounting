package org.shevliakov.collegeaccounting.database.repository;

import org.shevliakov.collegeaccounting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  User getByUsername(String username);
}
