package me.ilizin.spring_demo.springboot_demo.api_rest_with_security_demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// Since we've defined our users here, spring boot will not use the user/password from the application.properties file
@Configuration
public class ApiRestDemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123") // {noop} indicates the password s stored as plain text
                .roles("USER") // roles might be any string
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "api/advertisements").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "api/advertisements/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "api/advertisements").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "api/advertisements").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "api/advertisements/**").hasRole("ADMIN")
        );

        httpSecurity.httpBasic(Customizer.withDefaults());

        /* Disable Cross Site Request Forgery (CSRF)
           In general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH*/
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }
}
