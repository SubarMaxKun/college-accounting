package org.shevliakov.collegeaccounting.database.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring configuration class. Contains beans for: - Application context - Data source - Entity
 * manager factory - Transaction manager - Exception translation
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("org.shevliakov.collegeaccounting.database.repository")
@ComponentScan("org.shevliakov.collegeaccounting")
public class SpringConfig {

  /**
   * Bean for application context.
   *
   * @return application context
   */
  @Bean
  public AnnotationConfigApplicationContext applicationContext() {
    return new AnnotationConfigApplicationContext();
  }

  /**
   * Bean for data source.
   *
   * @return data source
   */
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost:3306/college_accounting");
    dataSource.setUsername("root");
    dataSource.setPassword("1234");
    return dataSource;
  }

  /**
   * Bean for entity manager factory.
   *
   * @return entity manager factory
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan("org.shevliakov.collegeaccounting.entity");

    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaProperties(additionalProperties());

    return em;
  }

  /**
   * Bean for transaction manager.
   *
   * @return transaction manager
   */
  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

    return transactionManager;
  }

  /**
   * Bean for exception translation.
   *
   * @return exception translation
   */
  @Bean
  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
    return new PersistenceExceptionTranslationPostProcessor();
  }

  /**
   * Additional properties for entity manager factory.
   *
   * @return additional properties
   */
  Properties additionalProperties() {
    Properties properties = new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto", "none");
    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");

    return properties;
  }
}
