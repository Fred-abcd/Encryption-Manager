package de.informatik.encryptions;

import de.informatik.Main;
import de.informatik.encryptions.impl.Md5Encryption;
import de.informatik.encryptions.impl.TestEncryption;
import de.informatik.utils.InputHelper;
import de.informatik.utils.Logger;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class EncryptionManager {
    public ArrayList<Encryption> encryptions = new ArrayList<>();
    public List<String> encryptionNames = new ArrayList<>();

    public void init() {
        encryptions.add(new TestEncryption());
        encryptions.add(new Md5Encryption());

        for (Encryption encryption : encryptions) {
            encryptionNames.add(encryption.getName());
            encryption.onLoad();
        }
    }

    public String input(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String[] args = input.split("\\s+", 2);

        String methodName = args[0];
        String methodArgs = args.length > 1 ? args[1] : "";

        for (Encryption encryption : encryptions) {
            if (encryption.getName().equalsIgnoreCase(methodName)) {
                return encryption.onExecute(methodArgs);

            }
        }
        String mostSimilarString = InputHelper.findMostSimilarString(input, encryptionNames);
        Main.logger.logError("Module " + input + " not found! Did you mean " + Logger.ANSI_YELLOW + mostSimilarString + Logger.ANSI_RED + "?");
        return "ERROR";
    }
}
