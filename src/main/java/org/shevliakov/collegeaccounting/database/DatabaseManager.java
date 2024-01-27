package org.shevliakov.collegeaccounting.database;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

/**
 * Utility class for managing database connection.
 */
public final class DatabaseManager {

  private static EntityManagerFactory entityManagerFactory;

  /**
   * Private constructor to prevent instantiation.
   */
  private DatabaseManager() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Creates and returns EntityManager instance.
   * @return EntityManager instance.
   */
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