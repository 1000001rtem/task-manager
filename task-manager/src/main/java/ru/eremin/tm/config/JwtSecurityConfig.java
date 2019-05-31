package ru.eremin.tm.config;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.eremin.tm.filter.JwtTokenFilter;
import ru.eremin.tm.security.JwtTokenProvider;

/**
 * @autor av.eremin on 31.05.2019.
 */

public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenProvider jwtTokenProvider;

    private UserDetailsService userDetailsService;

    public JwtSecurityConfig(final JwtTokenProvider jwtTokenProvider, final UserDetailsService userDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(final HttpSecurity builder) throws Exception {
        JwtTokenFilter jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider, userDetailsService);
        builder.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class).antMatcher("/api/**");
    }
}
