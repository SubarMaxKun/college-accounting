package org.shevliakov.collegeaccounting.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Student extends Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NonNull
  @Column(name = "full_name")
  private String fullName;

  @NonNull
  @Column(name = "birth_date")
  private Date birthDate;

  @NonNull
  @Column(name = "address")
  private String address;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "group_code")
  private Group group;

  @NonNull
  @Column(name = "on_tck")
  private Boolean onTck;

  @NonNull
  @Column(name = "military_document")
  private String militaryDocument;

  @Column(name = "specialty_rank")
  private String specialtyAndRank;

  @NonNull
  @Column(name = "tck_name")
  private String tckName;

  @NonNull
  @Column(name = "tax_card")
  private String taxCardNumber;

  @Column(name = "notes")
  private String notes;
}
