package org.shevliakov.collegeaccounting.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {

  int id;
  String fullName;
  String birtDate;
  String address;
  int groupCode;
}
