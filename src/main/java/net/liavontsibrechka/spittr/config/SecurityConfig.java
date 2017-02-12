package net.liavontsibrechka.spittr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("Leonti").password("IamProgrammer").roles("USER").and()
//                .withUser("admin").password("superAdminPassword").roles("USER", "ADMIN");

//        auth.jdbcAuthentication().dataSource(dataSource)
                // custom queries
                // see default queries for "query contract"
//                .usersByUsernameQuery("select username, password, true from Spitter where username=?")
//                .authoritiesByUsernameQuery("select username, 'ROLE_USER' from Spitter where username=?")
//                .groupAuthoritiesByUsername("")
//                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));

        auth.ldapAuthentication()
                .userSearchBase("ou=people")
                .userSearchFilter("(uid={0})")
                .groupSearchBase("ou=groups")
                .groupSearchFilter("member={0}")
                // configuring server location
                .contextSource().url("ldap://habuma.com:389/dc=habuma,dc=com").and()
//                or using Spring Security provided server
//                .root("dc=habuma,dc=com").ldif("classpath:users.ldif")
                // for password comparison
                .passwordCompare()
                .passwordEncoder(new Md5PasswordEncoder())
                .passwordAttribute("passcode");


    }
}
