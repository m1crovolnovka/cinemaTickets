package io.denchik.cinemakursach.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.userDetailsService = customUserDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .authorizeHttpRequests(authorize -> authorize
                        //.requestMatchers("/login", "/register", "/register/save", "/movies", "/movies/{id:[0-9]+}", "/movies/search", "/css/**", "/js/**", "/assets/**").permitAll()
                        //.requestMatchers("/movies/*/tickets", "/movies/*/tickets/**", "/cart", "/cart/**", "/payment/**", "/card/**", "/erip/**", "/orders", "/orders/**", "/paymentAll/**", "/cardAll/**", "/eripAll/**").not().hasAuthority("ADMIN")
                       // .requestMatchers("/movies/*/tickets", "/movies/*/tickets/**", "/cart", "/cart/**", "/payment/**", "/card/**", "/erip/**", "/orders", "/orders/**", "/paymentAll/**", "/cardAll/**", "/eripAll/**", "/profile", "/profile/**").hasAuthority("USER")
                        //.anyRequest().hasAuthority("ADMIN")
                        .anyRequest().permitAll()

                )
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/movies",true)
                                .loginProcessingUrl("/login")
                                .failureHandler(((request, response, exception) -> {
                                    if(exception instanceof InternalAuthenticationServiceException){
                                        response.sendRedirect("/login?blocked=true");
                                    }
                                    else{
                                        response.sendRedirect("/login?error=true");
                                    }
                                }))
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                );

        return http.build();
    }
}



