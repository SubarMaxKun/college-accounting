package org.shevliakov.collegeaccounting.database.dao;

import org.shevliakov.collegeaccounting.entity.Group;

public interface GroupDao {

  Group getGroupById(Long id);

  Group getGroupByCode(String code);

  void persistGroup(Group group);

  void updateGroup(Group group);

  void deleteGroup(Group group);

}
