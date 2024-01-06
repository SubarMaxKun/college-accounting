package org.shevliakov.collegeaccounting;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.function.Consumer;

public class JPAIllustrationTest {

  private EntityManagerFactory entityManagerFactory;

  protected void setUp() {
    entityManagerFactory = createEntityManagerFactory(
        "org.shevliakov.college-accounting.persistence");
  }

  protected void tearDown() {
    entityManagerFactory.close();
  }

  public void inTransaction(Consumer<EntityManager> work) {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    try {
      transaction.begin();
      work.accept(entityManager);
      transaction.commit();
    } catch (Exception e) {
      if (transaction.isActive()) {
        transaction.rollback();
      }
      throw e;
    } finally {
      entityManager.close();
    }
  }

}