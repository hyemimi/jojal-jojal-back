package community.Jojal_Jojal.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ CSRF 보호 비활성화 (API 서버에서는 필요 없음)
                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/*").permitAll() // ✅ 회원가입 & 로그인 API는 인증 없이 허용
                        //.requestMatchers("/users/**", "/auth/tokens").permitAll() // ✅ 회원가입 & 로그인 API는 인증 없이 허용
                        //.anyRequest().authenticated() // ✅ 그 외 모든 요청은 인증 필요
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.disable()) // ✅ 기본 로그인 폼 비활성화
                .httpBasic(httpBasic -> httpBasic.disable()); // ✅ HTTP Basic 인증 비활성화

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // ✅ 비밀번호 암호화
    }
}
