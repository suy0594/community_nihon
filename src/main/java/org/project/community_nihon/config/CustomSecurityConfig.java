package org.project.community_nihon.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.project.community_nihon.security.CustomUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

    private final DataSource dataSource;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api/login", "/api/join").permitAll()
                .anyRequest().authenticated()
        );
        http.formLogin(form -> form
                .loginPage("/api/login")
                .defaultSuccessUrl("/api/home", true)
                .permitAll()
        );
        http.logout(logout -> logout
                .logoutUrl("/api/logout")
                .logoutSuccessUrl("/api/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        );

        http.rememberMe()
                .key("12345678")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(60*60*24*30);

        return http.build();

    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        log.info("--------web configure----------");

        return (web) -> web.ignoring().requestMatchers(PathRequest.
                                        toStaticResources().atCommonLocations());

    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

}
