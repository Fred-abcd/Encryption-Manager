package de.informatik.encryptions;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public abstract class Encryption {

    private final String name;
    private String plaintext;
    private String key;
    private String usage;

    public Encryption(String name, String usage) {
        this.name = name;
        this.usage = usage;
    }

    abstract public String onExecute(String plaintext) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    abstract public void onLoad();

    public String getUsage() {
        return usage;
    }

    public String getName() {
        return name;
    }

    public String getPlaintext() {
        return plaintext;
    }

    public String getKey() {
        return key;
    }
}
