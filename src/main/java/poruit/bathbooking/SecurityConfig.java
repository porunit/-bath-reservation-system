package poruit.bathbooking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .csrf(csrf -> csrf.disable())                      // выключаем CSRF (для простого REST-API)
          .authorizeHttpRequests(auth -> auth
              // позволяeм обращаться к h2-console (если включена)
              .requestMatchers("/h2-console/**").permitAll()
              // открываем доступ к вашим публичным ресурсам
              .requestMatchers("/locations/**", "/bathhouses/**", "/reservations/**")
                .permitAll()
              // всё остальное (если есть) — требует аутентификации
              .anyRequest().permitAll()
          )
          // чтобы H2-консоль не блокировалась фреймами
          .headers(headers -> headers.frameOptions(frame -> frame.disable()))
          // включаем CORS (возьмёт WebConfig выше)
          .cors(Customizer.withDefaults());

        return http.build();
    }
}
