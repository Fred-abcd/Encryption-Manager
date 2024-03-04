package de.informatik;

import de.informatik.encryptions.Encryption;
import de.informatik.encryptions.EncryptionManager;
import de.informatik.utils.Logger;

import java.util.Scanner;

public class Main {
    public static Logger logger = new Logger();
    public static EncryptionManager manager = new EncryptionManager();

    public static void main(String[] args) {
        logger.logInfo("Loading all Encryptions...");
        manager.init();
        logger.logSuccess("Loaded!");

        Scanner inputMenu = new Scanner(System.in);
        logger.logInfo("Please choose one of the following: ");

        for (Encryption e : manager.encryptions){
            logger.logInfo(e.getName());
        }

        manager.input(inputMenu.nextLine());
    }
}
