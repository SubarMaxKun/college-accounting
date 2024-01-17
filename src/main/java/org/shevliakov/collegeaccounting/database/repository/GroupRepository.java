package org.shevliakov.collegeaccounting.database.repository;

import org.shevliakov.collegeaccounting.entity.Group;

public interface GroupRepository {

  Group getGroupById(Long id);

  Group getGroupByCode(String code);

  void persistGroup(Group group);

  void updateGroup(Group group);

  void deleteGroup(Group group);

}
