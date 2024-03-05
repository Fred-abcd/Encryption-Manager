package de.informatik.encryptions.impl;

import de.informatik.encryptions.Encryption;

public class TestEncryption extends Encryption {

    public TestEncryption() {
        super("Test1");
    }

    @Override
    public String onExecute(String plaintext) {
        return "test";
    }

    @Override
    public void onLoad() {

    }
}
