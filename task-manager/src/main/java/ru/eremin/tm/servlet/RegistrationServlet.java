package ru.eremin.tm.servlet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.api.IUserService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.enumerated.Role;
import ru.eremin.tm.service.UserService;
import ru.eremin.tm.utils.PasswordHashUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @autor Eremin Artem on 16.05.2019.
 */

@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    @NotNull
    private final IUserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/registration.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        @Nullable final UserDTO user = getUser(req);
        if (user == null) resp.sendRedirect("registration");
        try {
            userService.persist(user);
        } catch (IncorrectDataException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("enter/project-list");
    }

    @Nullable
    private UserDTO getUser(final HttpServletRequest req) {
        @NotNull final UserDTO userDTO = new UserDTO();
        @Nullable final String login = req.getParameter("login");
        @Nullable final String password = req.getParameter("password");
        if (login == null || login.isEmpty()) return null;
        if (password == null || password.isEmpty()) return null;
        userDTO.setLogin(login);
        userDTO.setHashPassword(PasswordHashUtil.md5(password));
        userDTO.setRole(Role.USER);
        return userDTO;
    }
}
