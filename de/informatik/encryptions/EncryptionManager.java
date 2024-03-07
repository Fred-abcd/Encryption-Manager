package de.informatik.encryptions;

import de.informatik.Main;
import de.informatik.encryptions.impl.*;
import de.informatik.utils.InputHelper;
import de.informatik.utils.Logger;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class EncryptionManager {
    //Liste der Encryptions als Klassen
    public ArrayList<Encryption> encryptions = new ArrayList<>();

    //Liste der Namen der Encryptions
    public List<String> encryptionNames = new ArrayList<>();

    //initialisierung
    public void init() {
        encryptions.add(new Md5Encryption());
        encryptions.add(new SHA1Encrypt());
        encryptions.add(new SHA224Encrypt());
        encryptions.add(new SHA256Encrypt());
        encryptions.add(new SHA384Encrypt());
        encryptions.add(new SHA512Encrypt());


        for (Encryption encryption : encryptions) {
            encryptionNames.add(encryption.getName());
            encryption.onLoad();
        }
    }

    //Verwaltung des Inputs, teilung auf Name und Text
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

    //Name einer Encryption einer Klasse (Encryption) zuordnen
    public Encryption findEncryptionByName(String name) {
        for (Encryption e : encryptions) {
            if (e.getName().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }
}
