package org.shevliakov.collegeaccounting.database.repository;

import java.util.List;
import org.shevliakov.collegeaccounting.entity.Group;

public interface GroupRepository {

  List<Group> getAllGroups();

  Group getGroupById(Long id);

  Group getGroupByCode(String code);

  void persistGroup(Group group);

  void updateGroup(Group group);

  void deleteGroup(Group group);

}
