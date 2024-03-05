package de.informatik.encryptions;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public abstract class Encryption {

    private final String name;
    private String plaintext;
    private String key;

    public Encryption(String name) {
        this.name = name;
    }

    public Encryption(String name, String key) {
        this.name = name;
        this.key = key;
    }

    abstract public String onExecute(String plaintext) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    abstract public void onLoad();

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
