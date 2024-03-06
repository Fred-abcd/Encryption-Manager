package de.informatik.encryptions.impl;

import de.informatik.Main;
import de.informatik.encryptions.Encryption;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHA256Encrypt extends Encryption {
    public SHA256Encrypt() {
        super("sha256", "sha256 <text>");
    }

    @Override
    public String onExecute(String plaintext) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
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
        Main.logger.logInfo("Loading SHA-256 encryption...");

    }
}
