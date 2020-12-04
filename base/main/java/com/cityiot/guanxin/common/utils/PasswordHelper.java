package com.cityiot.guanxin.common.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordHelper {
    private static RandomNumberGenerator randomNumberGenerator =
            new SecureRandomNumberGenerator();
    private static String algorithmName = "md5";
    private static final int hashIterations = 1;
    public static String encryptPassword(String password) {
        String newPassword = new SimpleHash(
                algorithmName,
                password,
                ByteSource.Util.bytes("test"),
                hashIterations).toHex();
        return newPassword;
    }
}
