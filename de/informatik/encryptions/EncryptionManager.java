package de.informatik.encryptions;

import de.informatik.Main;
import de.informatik.utils.InputHelper;
import de.informatik.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class EncryptionManager {
    public ArrayList<Encryption> encryptions = new ArrayList<>();
    public List<String> encryptionNames = new ArrayList<>();

    public void init() {

        for (Encryption encryption : encryptions) {
            encryptionNames.add(encryption.getName());
            encryption.onLoad();
        }
    }

    public void input(String input) {
        String[] args = input.split("\\s+");
        for (Encryption encryption : encryptions) {
            if (encryption.getName().equalsIgnoreCase(input)) {
                //noch falsch
                encryption.onExecute(args[1]);
                return;
            }
        }
        String mostSimilarString = InputHelper.findMostSimilarString(input, encryptionNames);
        Main.logger.logError("Module " + input + " not found! Did you mean " + Logger.ANSI_YELLOW + mostSimilarString + Logger.ANSI_RED + "?");
        return;
    }

}
