package com.style.server;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            try (InputStream is = Files.newInputStream(Paths.get(file));
                 DigestInputStream dis = new DigestInputStream(is, md)) {
                byte[] buffer = new byte[2048];
                //noinspection StatementWithEmptyBody
                while (dis.read(buffer) > 0) {

                }
                byte[] digest = dis.getMessageDigest().digest();
                return Base64.getUrlEncoder().encodeToString(digest).trim();
            } catch (Exception e) {
                return null;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
