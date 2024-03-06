package de.informatik.encryptions.impl;

import de.informatik.encryptions.Encryption;
import de.informatik.Main;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Md5Encryption extends Encryption {

    public Md5Encryption() {
        super("md5", "md5 <text>");
    }

    @Override
    public String onExecute(String plaintext) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(plaintext.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            Main.logger.logInfo("MD5: " + sb.toString());
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            return "md5 ERROR.";
        }
    }

    @Override
    public void onLoad() {
        Main.logger.logInfo("Loading MD5 encryption...");

    }
}
