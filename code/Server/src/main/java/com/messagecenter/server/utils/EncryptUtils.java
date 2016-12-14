package com.messagecenter.server.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Jared on 16/12/8.
 */
public class EncryptUtils {
    BCryptPasswordEncoder encoder;

    public static String encrypt(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static boolean match(String rawPassword, String encryptPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encryptPassword);
    }
}
