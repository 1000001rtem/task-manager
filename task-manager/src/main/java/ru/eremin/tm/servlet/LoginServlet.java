package ru.eremin.tm.servlet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.service.AuthService;
import ru.eremin.tm.util.PasswordHashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @autor av.eremin on 13.05.2019.
 */

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @Nullable final String login = req.getParameter("login");
        @Nullable final String password = PasswordHashUtil.md5(req.getParameter("password"));
        @Nullable final UserDTO user = AuthService.INSTANCE.check(login, password);
        if (user == null) req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
        else {
            @NotNull final HttpSession session = req.getSession();
            session.setAttribute("auth", true);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userRole", user.getRole());
            resp.sendRedirect("enter/project-list");
        }
    }

}
