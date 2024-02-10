package org.shevliakov.collegeaccounting.database.repository;

import java.util.List;
import org.shevliakov.collegeaccounting.entity.Student;

public interface StudentRepository {

  List<Student> getAllStudents();

  Student getStudentById(Long id);

  Student getStudentByFullName(String fullName);

  void persistStudent(Student student);

  void updateStudent(Student student);

  void deleteStudent(Student student);

}
