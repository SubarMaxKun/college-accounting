package org.shevliakov.collegeaccounting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NonNull
  @Column(name = "username")
  private String username;

  @NonNull
  @Column(name = "password")
  private String password;

  @NonNull
  @Column(name = "is_admin")
  private Boolean admin;

  @NonNull
  @Column(name = "rw_permission")
  private Boolean readAndWritePermission;
}
