package org.shevliakov.collegeaccounting.database.repository;

import java.sql.Date;
import java.util.List;
import org.shevliakov.collegeaccounting.entity.Group;
import org.shevliakov.collegeaccounting.entity.Student;

public interface StudentRepository {

  List<Student> getAllStudents();

  Student getStudentById(Long id);

  Student getStudentByFullName(String fullName);

  List<Student> getStudentsByGroup(Group group);

  void persistStudent(Student student);

  void updateStudent(Student student);

  void deleteStudent(Student student);

}
