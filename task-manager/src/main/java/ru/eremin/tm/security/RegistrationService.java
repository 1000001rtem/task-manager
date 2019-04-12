package ru.eremin.tm.security;

import org.jetbrains.annotations.Nullable;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.service.api.IUserService;

/**
 * @autor av.eremin on 12.04.2019.
 */

public class RegistrationService implements IRegistrationService {

    @Nullable
    private IUserService userService;

    public RegistrationService(@Nullable final IUserService userService) {
        if (userService == null) return;
        this.userService = userService;
    }

    @Override
    public void registration(@Nullable final UserDTO userDTO) {
        if (userDTO == null) return;
        userService.persist(userDTO);
    }

}
