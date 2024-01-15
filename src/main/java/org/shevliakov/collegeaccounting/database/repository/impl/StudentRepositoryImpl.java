package org.shevliakov.collegeaccounting.database.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.shevliakov.collegeaccounting.database.DatabaseManager;
import org.shevliakov.collegeaccounting.database.dao.StudentDao;
import org.shevliakov.collegeaccounting.entity.Student;

public class StudentDaoImpl implements StudentDao {

  EntityManager entityManager = DatabaseManager.getEntityManager();

  @Override
  public Student getStudentById(Long id) {
    return entityManager.find(Student.class, id);
  }

  @Override
  public Student getStudentByFullName(String fullName) {
    TypedQuery<Student> query = entityManager.createQuery(
        "SELECT s FROM Student s WHERE s.fullName = :fullName", Student.class);
    query.setParameter("fullName", fullName);
    try {
      return query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public void persistStudent(Student student) {
    entityManager.persist(student);
    entityManager.getTransaction().commit();
  }

  @Override
  public void updateStudent(Student student) {
    Student updatedStudent = entityManager.merge(student);
    entityManager.getTransaction().commit();
  }

  @Override
  public void deleteStudent(Student student) {
    entityManager.remove(student);
    entityManager.getTransaction().commit();
  }
}
