package br.cefetmg.restaurante.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration{
    private final SecurityFilter securityFilter;

    @Value("${springdoc.swagger-ui.enabled:false}")
    private boolean swaggerEnabled;

    // Defina uma lista de caminhos brancos (whitelist) para o Swagger
    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**"
    };

    public static final String AUTH_URI = "/api/v1/auth/**";

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(Arrays.asList("*"));
                        configuration.setAllowedMethods(List.of("POST", "PUT", "PATCH", "GET", "OPTIONS", "DELETE"));
                        configuration.setAllowedHeaders(Arrays.asList("*"));
                        return configuration;
                    }))
                //deixa as requisicoes stateless ou seja via toke
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize->authorize
                                .requestMatchers(AUTH_URI ).permitAll() // Login continua público
                                .requestMatchers(SWAGGER_WHITELIST ).permitAll() //Swagger fica publico
                                .anyRequest().authenticated() // Todo o resto exige apenas estar logado
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
