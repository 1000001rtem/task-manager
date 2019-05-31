package ru.eremin.tm.filter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;
import ru.eremin.tm.exeption.InvalidJwtAuthenticationException;
import ru.eremin.tm.security.JwtTokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @autor av.eremin on 30.05.2019.
 */

public class JwtTokenFilter2 extends GenericFilterBean {

    @NotNull
    private final UserDetailsService userDetailsService;

    @NotNull
    private final JwtTokenProvider tokenProvider;

    public JwtTokenFilter2(@NotNull final JwtTokenProvider tokenProvider, @NotNull final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        @NotNull final HttpServletRequest req = (HttpServletRequest) servletRequest;
        @NotNull final HttpServletResponse resp = (HttpServletResponse) servletResponse;
        @Nullable final String token = tokenProvider.resolveToken(req);
        try {
            tokenProvider.validateToken(token);
        } catch (InvalidJwtAuthenticationException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            e.printStackTrace();
            return;
        }
        @Nullable final String login = tokenProvider.getUserLogin(token);
        @Nullable final UserDetails userDetails = userDetailsService.loadUserByUsername(login);
        if (login == null || login.isEmpty() || userDetails == null)
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        @NotNull final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(login, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
