package com.daineka.configuration;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class AppConfigTest {

    @Mock
    private Environment mockEnv;
    @InjectMocks
    private AppConfig appConfig;

    @Test
    void testDataSourceBeanCreation() throws IOException {
        Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("app.properties"));

        Mockito.when(mockEnv.getRequiredProperty("db.url")).thenReturn(properties.getProperty("db.url"));
        Mockito.when(mockEnv.getRequiredProperty("db.username")).thenReturn(properties.getProperty("db.username"));
        Mockito.when(mockEnv.getRequiredProperty("db.password")).thenReturn(properties.getProperty("db.password"));
        Mockito.when(mockEnv.getRequiredProperty("db.driver")).thenReturn(properties.getProperty("db.driver"));

        DataSource dataSource = appConfig.dataSource();
        assertNotNull(dataSource);
        assert dataSource instanceof HikariDataSource;
    }

    @Test
    void testEntityManagerFactoryBeanCreation() {
        Mockito.when(mockEnv.getRequiredProperty("db.entitymanager.packages.to.scan")).thenReturn("com.daineka.entity");
        Mockito.when(mockEnv.getRequiredProperty("db.hibernate.dialect")).thenReturn("org.hibernate.dialect.PostgreSQLDialect");
        Mockito.when(mockEnv.getRequiredProperty("db.hibernate.show_sql")).thenReturn("true");

        DataSource mockDataSource = Mockito.mock(DataSource.class);
        LocalContainerEntityManagerFactoryBean entityManagerFactory = appConfig.entityManagerFactory(mockDataSource);

        assertNotNull(entityManagerFactory);
    }

    @Test
    void testTransactionManagerBeanCreation() {
        EntityManagerFactory mockEntityManagerFactory = Mockito.mock(EntityManagerFactory.class);
        PlatformTransactionManager transactionManager = appConfig.transactionManager(mockEntityManagerFactory);

        assertNotNull(transactionManager);
    }

    @Test
    void testBookMapperBeanCreation() {
        assertNotNull(appConfig.bookMapper());
    }

    @Test
    void testAuthorMapperBeanCreation() {
        assertNotNull(appConfig.authorMapper());
    }

    @Test
    void testGenreMapperBeanCreation() {
        assertNotNull(appConfig.genreMapper());
    }

    @Test
    void testAuthorWithBooksMapperBeanCreation() {
        assertNotNull(appConfig.authorWithBooksMapper());
    }

    @Test
    void testBookWithAuthorAndGenresMapperBeanCreation() {
        assertNotNull(appConfig.bookWithAuthorAndGenresMapper());
    }
}