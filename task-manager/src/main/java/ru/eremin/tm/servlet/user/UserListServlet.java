package ru.eremin.tm.servlet.user;

import org.jetbrains.annotations.NotNull;
import ru.eremin.tm.api.IUserService;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@WebServlet(urlPatterns = "/enter/admin/user-list")
public class UserListServlet extends HttpServlet {

    @NotNull
    private final IUserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @NotNull final List<UserDTO> users = userService.findAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/pages/enter/user-list.jsp").forward(req, resp);
    }

}
