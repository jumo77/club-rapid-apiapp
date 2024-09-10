package xyz.rapid.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import xyz.rapid.jwt.JWTFilter;
import xyz.rapid.jwt.JWTUtil;
import xyz.rapid.jwt.LoginFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
//        csrf 방어 방법인 서버에 저장하는 기본 방식이 아닌 JWT 를 이용하기에 필요 없는 csrf 방어를 해제한다
                .csrf(AbstractHttpConfigurer::disable)
//        로그인 방식이 form 형식이 아니기에 기본값을 끈다
                .formLogin(AbstractHttpConfigurer::disable)
//        경로별 권한 부여
                .authorizeHttpRequests((auth)-> auth
                        .requestMatchers("/login", "/", "/member","/maindata","/frontend","/backend","/mobile").permitAll()
                        .requestMatchers("/admin").hasRole("admin")
                        .anyRequest().authenticated())
//        filter UPAF -> 커스텀 필터(LoginFilter), jwtUtil 주입
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil)
                        , UsernamePasswordAuthenticationFilter.class)
//        jwtFilter 등록
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class)
//        세션 설정
                .sessionManagement((session)->session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
