package net.liavontsibrechka.spittr.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("net.liavontsibrechka.spittr.data")
public class DBConfig {
    // JNDI DS
//    @Profile("production")
//    @Bean
//    public JndiObjectFactoryBean dataSource() {
//        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//        jndiObjectFactoryBean.setJndiName("jdbc/SpittrDS");
//        jndiObjectFactoryBean.setResourceRef(true);
//        jndiObjectFactoryBean.setProxyInterface(DataSource.class);
//
//        return jndiObjectFactoryBean;
//    }

    // Apache Commons Connection Pool
//    @Profile("qa")
//    @Bean
//    public BasicDataSource dataSource() {
//        BasicDataSource ds = new BasicDataSource();
//        ds.setDriverClassName("org.h2.Driver");
//        ds.setUrl("jdbc:h2:tcp://localhost/~/spitter");
//        ds.setUsername("sa");
//        ds.setPassword("");
//        ds.setInitialSize(5);
//        ds.setMaxActive(10);
//        return ds;
//    }

//    JDBC driver-based
//    @Bean
//    public DataSource dataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource();
//        ds.setDriverClassName("org.h2.Driver");
//        ds.setUrl("jdbc:h2:tcp://localhost/~/spitter");
//        ds.setUsername("sa");
//        ds.setPassword("");
//        return ds;
//    }

    // Embedded DS
    @Profile("development")
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();
    }
}
