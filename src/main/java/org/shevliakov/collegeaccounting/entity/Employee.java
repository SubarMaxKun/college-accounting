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
@Table(name = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee extends Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "rank")
  private Rank rank;

  @NonNull
  @Column(name = "full_name")
  private String fullName;

  @NonNull
  @Column(name = "birth_date")
  private Date birthDate;

  @NonNull
  @Column(name = "address_living")
  private String addressOfLiving;

  @NonNull
  @Column(name = "address_registered")
  private String addressOfRegistration;

  @NonNull
  @Column(name = "tck_name")
  private String tckName;

  @NonNull
  @Column(name = "family")
  private String family;

  @NonNull
  @Column(name = "job_info")
  private String jobInfo;

  @NonNull
  @Column(name = "registration_number")
  private String registrationNumber;

  @NonNull
  @Column(name = "military_specialty")
  private String militarySpecialty;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "training")
  private Training training;

  @NonNull
  @Column(name = "accounting_category")
  private String accountingCategory;

  @NonNull
  @Column(name = "degree")
  private String degree;

  @NonNull
  @Column(name = "id_info")
  private String idInfo;
}
