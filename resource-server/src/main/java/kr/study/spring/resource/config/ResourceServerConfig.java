package kr.study.spring.resource.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity(debug = true)
public class ResourceServerConfig {

    @Value("${secret.key}")
    private String secretKey;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .mvcMatcher("/sample/**")
                .authorizeRequests()
                .mvcMatchers("/sample/**")
                .access("hasAuthority('SCOPE_read')")
                .and()
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults())
                );
        return http.build();
    }


    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] key = secretKey.getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = new SecretKeySpec(key, "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKey)
                .build();
    }
}
