package ru.eremin.tm.service.security;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.eremin.tm.api.service.IUserService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.entity.enumerated.Role;

/**
 * @autor av.eremin on 29.05.2019.
 */

@Service(UserDetailsServiceBean.NAME)
public class UserDetailsServiceBean implements UserDetailsService {

    public static final String NAME = "userDetailsService";

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        @Nullable final UserDTO user = findByLogin(login);
        if (user == null) throw new UsernameNotFoundException("User not found");
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        builder = org.springframework.security.core.userdetails.User.withUsername(login);
        builder.password(user.getHashPassword());
        @NotNull final Role userRole = user.getRole();
        builder.roles(userRole.name());
        @NotNull final UserDetails userDetails = builder.build();
        return userDetails;
    }

    @Nullable
    private UserDTO findByLogin(@Nullable final String login) {
        if (login == null || login.isEmpty()) return null;
        try {
            return userService.findByLogin(login);
        } catch (IncorrectDataException e) {
            e.printStackTrace();
            return null;
        }
    }
}
