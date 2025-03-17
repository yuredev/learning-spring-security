package learning.security.secure_post.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desativa CSRF (opcional para APIs)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()) // Permite todas as requisições
                .httpBasic(withDefaults()); // Habilita autenticação básica (opcional)

        return http.build();
    }
}
