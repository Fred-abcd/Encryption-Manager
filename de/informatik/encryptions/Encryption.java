package de.informatik.encryptions;

public abstract class Encryption {

    public final String name;
    public String plaintext;
    public String key;


    public Encryption(String name) {
        this.name = name;
    }

    public Encryption(String name, String key) {
        this.name = name;
        this.key = key;
    }

    abstract public void onExecute(String plaintext);
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
