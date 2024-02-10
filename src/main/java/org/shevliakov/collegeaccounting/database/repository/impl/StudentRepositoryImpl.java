package org.shevliakov.collegeaccounting.database.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.shevliakov.collegeaccounting.database.DatabaseManager;
import org.shevliakov.collegeaccounting.database.repository.StudentRepository;
import org.shevliakov.collegeaccounting.entity.Student;

public class StudentRepositoryImpl implements StudentRepository {

  EntityManager entityManager = DatabaseManager.getEntityManager();

  @Override
  public List<Student> getAllStudents() {
    TypedQuery<Student> query = entityManager.createQuery(
        "SELECT s FROM Student s", Student.class);
    return query.getResultList();
  }

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
    if (student.getId() == null){
      entityManager.persist(student);
      entityManager.getTransaction().commit();
    } else {
      updateStudent(student);
    }
  }

  @Override
  public void updateStudent(Student student) {
    entityManager.merge(student);
    entityManager.getTransaction().commit();
  }

  @Override
  public void deleteStudent(Student student) {
    entityManager.remove(student);
    entityManager.getTransaction().commit();
  }
}
