package ru.eremin.tm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.eremin.tm.filter.JwtTokenFilter;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.security.JwtTokenProvider;

/**
 * @autor av.eremin on 29.05.2019.
 */

@EnableWebSecurity
public class WebSecurityConfig {

    @Configuration
    @Order(1)
    public static class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private UserDetailsService service;

        @Autowired
        private JwtTokenProvider jwtTokenProvider;

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http
                    .addFilterBefore(new JwtTokenFilter(jwtTokenProvider, service), UsernamePasswordAuthenticationFilter.class).antMatcher("/api/**");
        }

        @Override
        public void configure(final WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/api/auth/**");
        }

    }

    @Configuration
    public static class FormLoginSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private UserDetailsService service;

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(service).passwordEncoder(passwordEncoder());
        }

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .and()
                    .authorizeRequests()
                    .antMatchers("/enter/**")
                    .hasAnyRole(Role.USER.name(), Role.ADMIN.name())
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/enter/menu")
                    .and()
                    .logout().permitAll()
                    .and()
                    .csrf().disable();
        }

    }

}
