package de.informatik.encryptions.impl;

import de.informatik.Main;
import de.informatik.encryptions.Encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class SHA384Encrypt extends Encryption {
    public SHA384Encrypt() {
        super("sha384", "sha384 <text>");
    }

    @Override
    public String onExecute(String plaintext) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-384");
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

    }
}
