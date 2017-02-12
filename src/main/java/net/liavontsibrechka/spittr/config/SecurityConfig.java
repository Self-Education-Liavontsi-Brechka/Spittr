package net.liavontsibrechka.spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import javax.activation.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("Leonti").password("IamProgrammer").roles("USER").and()
//                .withUser("admin").password("superAdminPassword").roles("USER", "ADMIN");

        auth.jdbcAuthentication().dataSource(dataSource)
                // custom queries
                // see default queries for "query contract"
                .usersByUsernameQuery("select username, password, true from Spitter where username=?")
                .authoritiesByUsernameQuery("select username, 'ROLE_USER' from Spitter where username=?")
                .groupAuthoritiesByUsername("")
                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
    }
}
