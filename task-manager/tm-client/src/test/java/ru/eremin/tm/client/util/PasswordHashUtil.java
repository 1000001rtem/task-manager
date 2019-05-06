package ru.eremin.tm.client.util;

import org.jetbrains.annotations.NotNull;

/**
 * @autor av.eremin on 11.04.2019.
 */

public final class PasswordHashUtil {

    public static String md5(final String md5) {
        if (md5 == null) return null;
        try {
            @NotNull final java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            @NotNull final byte[] array = md.digest(md5.getBytes());
            @NotNull final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}

