package com.style.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * jinyalin
 * on 2017/4/25.
 */
public class ChecksumUtil {
    public static String getChecksum(File file) {
        return getChecksum(file.getName());
    }

    public static String getChecksum(String file) {
        MessageDigest md;
        InputStream is = null;
        try {
            md = MessageDigest.getInstance("MD5");
            is = Files.newInputStream(Paths.get(file));
            byte[] digest = md.digest();
            return Base64.getUrlEncoder().encodeToString(digest);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
