package ru.eremin.tm.rest;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.eremin.tm.api.service.IUserService;
import ru.eremin.tm.exeption.IncorrectDataException;
import ru.eremin.tm.model.dto.UserDTO;
import ru.eremin.tm.model.dto.web.ChangePasswordDTO;
import ru.eremin.tm.model.dto.web.ResultDTO;

/**
 * @autor av.eremin on 05.06.2019.
 */

@RestController
@RequestMapping(value = "/api/account")
public class UserAccountRestController {

    @NotNull
    @Autowired
    private IUserService userService;

    @NotNull
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/changePassword", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResultDTO changePassword(@RequestBody @Nullable final ChangePasswordDTO changePasswordDTO) throws IncorrectDataException {
        if (changePasswordDTO == null) return new ResultDTO(false);
        @Nullable final String oldPassword = changePasswordDTO.getOldPassword();
        @Nullable final String newPassword = changePasswordDTO.getNewPassword();

        if (oldPassword == null || oldPassword.isEmpty()) return new ResultDTO(false);
        if (newPassword == null || newPassword.isEmpty()) return new ResultDTO(false);

        @NotNull final UserDTO userDTO = userService.findOne(changePasswordDTO.getUserId());
        if (!passwordEncoder.matches(oldPassword, userDTO.getHashPassword())) return new ResultDTO(false);
        userDTO.setHashPassword(passwordEncoder.encode(newPassword));
        userService.update(userDTO);
        return new ResultDTO(true);
    }

}
