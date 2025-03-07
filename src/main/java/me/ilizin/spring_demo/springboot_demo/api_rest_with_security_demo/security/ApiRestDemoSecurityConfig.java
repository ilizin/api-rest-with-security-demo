package me.ilizin.spring_demo.springboot_demo.api_rest_with_security_demo.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

// Since we've defined our users here, spring boot will not use the user/password from the application.properties file
@Configuration
public class ApiRestDemoSecurityConfig {

    // Uncomment this to use hard-coding users instead of the ones from the users table
    /*@Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123") // {noop} indicates the password s stored as plain text
                .roles("USER") // roles might be any string
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("USER", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("USER", "MANAGER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john, mary, susan);
    }*/

    /*  This tells Spring security to use JDBC authentication with our data source.
        Spring Security will look in a table called users and another table called authorities.
        It knows the exact column names that it'll use. Basically Spring security can read
        user account ino from database, by default you've to follow Spring security's
        predefined table schemas. */
    /*@Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        /* Spring security queries the database for each login, therefore if we change the password into
           the database, it won't be necessary to restart the application. */
      /*  return new JdbcUserDetailsManager(dataSource);
    }*/

    // Uncomment if we want to use custom tables for the spring security authentication and authorization
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        // Define how to find users and how to find roles
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/greetings").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/api/greetings").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/greetings").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/greetings").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/greetings").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/greetings").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/greetings").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/greetings").hasRole("ADMIN")
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
        )       //TODO frameOptions is deprecated fix it.
                .headers(headers -> headers.frameOptions().disable());

        httpSecurity.httpBasic(Customizer.withDefaults());

        /* Disable Cross Site Request Forgery (CSRF)
           In general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH*/
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }
}
