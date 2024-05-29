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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.sql.results.graph.Fetch;

@Entity
@Table(name = "lecturers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Lecturer extends Person{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NonNull
  @Column(name = "full_name")
  private String fullName;

  @NonNull
  @Column(name = "position")
  private String position;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "category")
  private QualificationCategory category;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "title")
  private PedagogicalTitle title;

  @NonNull
  @Column(name = "last_certification")
  private Integer lastCertification;

  @NonNull
  @Column(name = "next_certification")
  private Integer nextCertification;

  @NonNull
  @Column(name = "hours")
  private String hours;
}
