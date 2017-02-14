package net.liavontsibrechka.spittr.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    DataSource dataSource;

//    @Autowired
//    SpitterRepository spitterRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("Leonti").password("IamProgrammer").roles("USER").and()
                .withUser("admin").password("superAdminPassword").roles("USER", "ADMIN");

//        auth.jdbcAuthentication().dataSource(dataSource)
                // custom queries
                // see default queries for "query contract"
//                .usersByUsernameQuery("select username, password, true from Spitter where username=?")
//                .authoritiesByUsernameQuery("select username, 'ROLE_USER' from Spitter where username=?")
//                .groupAuthoritiesByUsername("")
//                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));

//        auth.ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}")
                // configuring server location
//                .contextSource().url("ldap://habuma.com:389/dc=habuma,dc=com").and()
        // or using Spring Security provided server
//                .root("dc=habuma,dc=com").ldif("classpath:users.ldif")
                // for password comparison
//                .passwordCompare()
//                .passwordEncoder(new Md5PasswordEncoder())
//                .passwordAttribute("passcode");

//        auth.userDetailsService(new SpitterUserService(spitterRepository));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // default login page
//                .formLogin().and()
                .formLogin().loginPage("/login").and()
                .rememberMe().tokenValiditySeconds(2419200).key("spittrKey").and()
                .logout().logoutSuccessUrl("/")
                // for changing logout path of LogoutFilter
//                .logoutUrl("/signout")
                .and()
                .httpBasic().realmName("Spittr").and()
                .authorizeRequests()
                .antMatchers("/spitters/me").hasRole("SPITTER")
                // SpEL alternative
                .antMatchers("/spitter/{username}").access("hasRole('ROLE_SPITTER') and hasIpAddress('192.168.1.2')")
                .antMatchers(HttpMethod.POST, "/spittles").authenticated()
                .anyRequest().permitAll()
                .and().requiresChannel()
                .antMatchers("/spitter/form").requiresSecure()
                .antMatchers("/").requiresInsecure();
        // for accepting csrf token properly in JSP
        // “<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        // not very good at all
//                .and().csrf().disable();
    }
}
