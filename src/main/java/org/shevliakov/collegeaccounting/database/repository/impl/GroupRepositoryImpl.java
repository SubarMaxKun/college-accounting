package org.shevliakov.collegeaccounting.database.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.Type;
import javax.swing.text.html.parser.Entity;
import org.shevliakov.collegeaccounting.database.DatabaseManager;
import org.shevliakov.collegeaccounting.database.dao.GroupDao;
import org.shevliakov.collegeaccounting.entity.Group;

public class GroupDaoImpl implements GroupDao {
  EntityManager entityManager = DatabaseManager.getEntityManager();

  @Override
  public Group getGroupById(Long id) {
    return entityManager.find(Group.class, id);
  }

  @Override
  public Group getGroupByCode(String code) {
    TypedQuery<Group> query = entityManager.createQuery(
        "SELECT g FROM Group g WHERE g.code = :code", Group.class);
    query.setParameter("code", code);
    try {
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public void persistGroup(Group group) {
    entityManager.persist(group);
    entityManager.getTransaction().commit();
  }

  @Override
  public void updateGroup(Group group) {
    Group updatedGroup = entityManager.merge(group);
    entityManager.getTransaction().commit();
  }

  @Override
  public void deleteGroup(Group group) {
    entityManager.remove(group);
    entityManager.getTransaction().commit();
  }
}
