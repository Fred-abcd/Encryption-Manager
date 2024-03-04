package de.informatik.encryptions.impl;

import de.informatik.Main;
import de.informatik.encryptions.Encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encryption extends Encryption {
    public Md5Encryption() {
        super("md5");
    }

    @Override
    public void onExecute(String plaintext) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(plaintext.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            System.out.println(sb.toString());
        } catch (java.security.NoSuchAlgorithmException e) {
        }
    }

    @Override
    public void onLoad() {
        Main.logger.logInfo("Loading Md5...");
    }
}
