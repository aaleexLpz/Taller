package es.alex.taller.config;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@PropertySource({"classpath:application.properties"})
@EnableJpaRepositories(
        basePackages = {"es.alex.taller.model.repository"},
        entityManagerFactoryRef = "tallerEntityManager",
        transactionManagerRef = "tallerTransactionManager"
)
public class MysqlDBConfig {

    private final Environment env;

    @Value("${spring.datasource.url}")
    private String databaseUrl;

    @Value("${spring.datasource.username}")
    private String username;
    
    @Value("${spring.datasource.password}")
    private String password;
    
    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.min-idle}")
    private Integer minIdle;

    @Value("${spring.datasource.max-total}")
    private Integer maxTotal;

    @Value("${spring.datasource.max-wait-millis}")
    private Long maxWaitMillis;

    @Autowired
    public MysqlDBConfig(Environment env) {
        this.env = env;
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean tallerEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(tallerDataSource());
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.setPersistenceUnitName("tallerEntityManager");
        em.setPackagesToScan("es.alex.taller.model.entities");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.database-platform"));
        em.setJpaPropertyMap(properties);
        return em;
    }

    @Primary
    @Bean
    public DataSource tallerDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl(databaseUrl);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setMinimumIdle(minIdle);
        dataSource.setMaximumPoolSize(maxTotal);
        dataSource.setConnectionTimeout(maxWaitMillis);
        return dataSource;
    }


    @Primary
    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("tallerDataSource") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean
    public NamedParameterJdbcTemplate namedJdbcTemplate(JdbcTemplate template) {
        return new NamedParameterJdbcTemplate(template);
    }


    @Primary
    @Bean
    public PlatformTransactionManager tallerTransactionManager(LocalContainerEntityManagerFactoryBean tallerTransactionManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(tallerTransactionManager.getObject());
        return transactionManager;
    }

}
