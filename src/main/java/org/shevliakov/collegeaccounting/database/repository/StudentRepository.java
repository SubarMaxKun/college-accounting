package org.shevliakov.collegeaccounting.database.dao;

import org.shevliakov.collegeaccounting.entity.Student;

public interface StudentDao {

  Student getStudentById(Long id);

  Student getStudentByFullName(String fullName);

  void persistStudent(Student student);

  void updateStudent(Student student);

  void deleteStudent(Student student);

}
