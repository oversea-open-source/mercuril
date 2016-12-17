package com.messagecenter.common.utils;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Jared on 16/12/8.
 */
public class EncryptUtils {

    public static String encrypt(String password) {
        return DigestUtils.sha1Hex(password);
    }

    public static boolean match(String rawPassword, String encryptPassword) {
        return encryptPassword.equals(DigestUtils.sha1Hex(rawPassword));
    }
}
