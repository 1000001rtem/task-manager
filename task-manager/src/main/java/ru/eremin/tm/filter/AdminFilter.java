package ru.eremin.tm.filter;

import ru.eremin.tm.model.entity.enumerated.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@WebFilter(urlPatterns = {"/enter/admin/*"})
public class AdminFilter implements Filter {
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getSession().getAttribute("userRole").equals(Role.ADMIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else response.sendError(404);

    }

    @Override
    public void destroy() {

    }
}
