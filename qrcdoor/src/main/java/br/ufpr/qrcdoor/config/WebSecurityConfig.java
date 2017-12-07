package br.ufpr.qrcdoor.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import br.ufpr.qrcdoor.websocket.WebSocketMessageHandler;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ProfileAuthenticationProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .cors().and()
            .authorizeRequests()                
                .antMatchers("/static/**").permitAll()
                //.antMatchers("/img/**").permitAll()
                //.antMatchers("/css/**").permitAll()
                //.antMatchers("/js/**").permitAll()
                //.antMatchers("/pessoa/foto/**").permitAll()
                //.antMatchers("/estrutura/foto/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .httpBasic()
                .realmName(BasicAuthenticationEntryPoint.REALM)
                .authenticationEntryPoint(basicAuthEntryPoint())
                .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
            .logout()
//                .logoutUrl("/logout")
                .permitAll()
                .and()
            .formLogin()
                .defaultSuccessUrl("/")
                .failureHandler(authenticationFailureHandler())
//                .failureUrl("/login?error")
                .loginPage("/login")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider)
                .inMemoryAuthentication()
                .withUser("admin").password("1234").roles("ADMIN");
    }

    @Bean
    public BasicAuthenticationEntryPoint basicAuthEntryPoint() {
        return new BasicAuthenticationEntryPoint();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler authenticationFailureHandler() {
        return new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
//                super.onAuthenticationFailure(request, response, exception);;
                response.setStatus(javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED);
            }
        };
    }

}
