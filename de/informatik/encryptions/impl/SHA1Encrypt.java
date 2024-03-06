package de.informatik.encryptions.impl;

import de.informatik.Main;
import de.informatik.encryptions.Encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHA1Encrypt extends Encryption {
    public SHA1Encrypt() {
        super("sha1", "sha1 <text>");
    }

    @Override
    public String onExecute(String plaintext) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] encodedHash = digest.digest(plaintext.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            Main.logger.logError(e.getMessage());
            return "error";
        }
    }

    @Override
    public void onLoad() {
        Main.logger.logInfo("Loading SHA-1 encryption...");
    }
}
