package vn.tungnt.service.config;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = {"vn.tungnt.jpa.domain"})
@EnableJpaRepositories(basePackages = "vn.tungnt.service.repositories", 
						entityManagerFactoryRef = "entityManager", 
						transactionManagerRef = "transactionManager") // 2
public class PrimaryMysqlDBConfiguration {

	@Bean(name = "dataSource") // 3
	@Primary
	@ConfigurationProperties(prefix = "mysql.datasource")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}

	@PersistenceContext(unitName = "primary") // 4
//	@Primary
	@Bean(name = "entityManager")
	public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(mysqlDataSource()).persistenceUnit("primary")
//				.packages("vn.tungnt.jpa.domain") // @EntityScan
//				.properties()
				.build();
	}

	@Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactoryBuilder builder) {
          JpaTransactionManager tm = new JpaTransactionManager();
          tm.setEntityManagerFactory(mySqlEntityManagerFactory(builder).getObject());
          tm.setDataSource(mysqlDataSource());
          return tm;
    }
}
