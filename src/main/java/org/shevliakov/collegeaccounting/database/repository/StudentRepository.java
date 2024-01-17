package org.shevliakov.collegeaccounting.database.repository;

import org.shevliakov.collegeaccounting.entity.Student;

public interface StudentRepository {

  Student getStudentById(Long id);

  Student getStudentByFullName(String fullName);

  void persistStudent(Student student);

  void updateStudent(Student student);

  void deleteStudent(Student student);

}
