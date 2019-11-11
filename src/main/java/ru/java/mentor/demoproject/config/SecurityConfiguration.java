package ru.java.mentor.demoproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import ru.java.mentor.demoproject.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new AuthenticationSuccessHandlerImpl();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        System.out.println("Start security");
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        httpSecurity
                .addFilterBefore(filter, CsrfFilter.class)
                .authorizeRequests()
                    .antMatchers("/",
                            "/login").permitAll()
                    .antMatchers("/reg", "/reg/**").permitAll()
                    .antMatchers("/admin", "/admin/**").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/")
                    .failureUrl("/?error")
                    .usernameParameter("name")
                    .passwordParameter("password")
                    .successHandler(myAuthenticationSuccessHandler())
                    .and()
                .exceptionHandling()
                    .accessDeniedPage("/access_denied")
                    .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/");
    }


    @Override
    public void configure(WebSecurity webSecurity) {
      webSecurity.ignoring()
              .antMatchers("/static/**")
              .antMatchers("/templates/**");
    }
}