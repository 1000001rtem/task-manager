package ru.eremin.tm.filter;

import org.jetbrains.annotations.Nullable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @autor av.eremin on 13.05.2019.
 */

@WebFilter(urlPatterns = {"/enter/*"})
public class AuthFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (isAuth(request)) filterChain.doFilter(servletRequest, servletResponse);
        else response.sendRedirect("/login");
    }

    private boolean isAuth(final HttpServletRequest request) {
        if (request == null) return false;
        @Nullable final HttpSession session = request.getSession();
        if (session == null) return false;
        if (session.getAttribute("userId") == null) return false;
        @Nullable final Object authorization = session.getAttribute("auth");
        if (authorization == null) return false;
        if (!(authorization instanceof Boolean)) return false;
        return (Boolean) authorization;
    }

    @Override
    public void destroy() {

    }

}
