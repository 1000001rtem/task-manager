package ru.eremin.tm.servlet.user;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.api.IUserService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@WebServlet(urlPatterns = "/enter/admin/user-remove")
public class UserRemoveServlet extends HttpServlet {

    @NotNull
    private final IUserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @Nullable final String id = req.getParameter("id");
        try {
            userService.remove(id);
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("user-list");
    }

}
