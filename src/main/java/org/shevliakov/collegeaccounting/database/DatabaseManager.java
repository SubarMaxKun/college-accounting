package org.shevliakov.collegeaccounting.database;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

public class DatabaseManager {

  private static EntityManagerFactory entityManagerFactory;

  public static EntityManager getEntityManager() {
    if (entityManagerFactory == null) {
      entityManagerFactory = createEntityManagerFactory(
          "org.shevliakov.college-accounting.persistence");
    }
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();
    entityTransaction.begin();
    return entityManager;
  }
}