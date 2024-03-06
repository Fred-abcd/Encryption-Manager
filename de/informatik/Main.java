package de.informatik;

import de.informatik.encryptions.EncryptionManager;
import de.informatik.utils.Logger;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;

public class Main extends JFrame {

    public static Logger logger = new Logger();
    private EncryptionManager manager = new EncryptionManager();
    private JTextArea logTextArea;
    private boolean encryptionsLoaded = false;

    public Main() {
        setTitle("Encryption Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 600);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(50, 50, 50));
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        logTextArea.setFont(new Font("Roboto", Font.PLAIN, 25));
        logTextArea.setBackground(new Color(30, 30, 30));
        logTextArea.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(50, 50, 50));

        JButton loadButton = createButton("Load Encryptions");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!encryptionsLoaded) {
                    loadEncryptions();
                } else {
                    logger.logInfo("Encryptions already loaded.");
                }
            }
        });
        buttonPanel.add(loadButton);

        JButton chooseButton = createButton("Choose Encryption");
        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseEncryption();
            }
        });
        buttonPanel.add(chooseButton);

        add(buttonPanel, BorderLayout.SOUTH);

        createThemesMenu();
        setLookAndFeel("Metal");

        setVisible(true);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(70, 70, 70));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    private void loadEncryptions() {
        logger.logInfo("Loading all Encryptions...");
        manager.init();
        logger.logSuccess("Loaded!");
        encryptionsLoaded = true;

        logTextArea.setText("");
        logger.logInfo("Available Encryptions:");
        for (String name : manager.encryptionNames) {
            logger.logInfo(name);
            logTextArea.append(name + " | usage: " + manager.findEncryptionByName(name).getUsage() + "\n");
        }
    }

    private void chooseEncryption() {
        if (!encryptionsLoaded) {
            logger.logError("Please load encryptions first.");
            return;
        }

        String chosenEncryption = JOptionPane.showInputDialog("Please choose an encryption:");
        if (chosenEncryption != null) {
            try {
                String result = manager.input(chosenEncryption);
                if (!result.isEmpty()) {
                    copyToClipboard(result);
                }
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                logger.logError(ex.getMessage());
            }
        }
    }

    private void createThemesMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu themesMenu = new JMenu("Themes");
        themesMenu.setForeground(Color.BLACK);
        themesMenu.setBackground(Color.WHITE);

        JMenuItem metalItem = createMenuItem("Metal");
        metalItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLookAndFeel("Metal");
            }
        });
        themesMenu.add(metalItem);

        JMenuItem nimbusItem = createMenuItem("Nimbus");
        nimbusItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLookAndFeel("Nimbus");
            }
        });
        themesMenu.add(nimbusItem);

        JMenuItem synthItem = createMenuItem("Synth");
        synthItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLookAndFeel("Synth");
            }
        });
        themesMenu.add(synthItem);

        JMenuItem windowsItem = createMenuItem("Windows");
        windowsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLookAndFeel("Windows");
            }
        });
        themesMenu.add(windowsItem);

        JMenuItem motifItem = createMenuItem("Motif");
        motifItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLookAndFeel("Motif");
            }
        });
        themesMenu.add(motifItem);

        menuBar.add(themesMenu);
        menuBar.setBackground(new Color(50, 50, 50));
        setJMenuBar(menuBar);
    }

    private JMenuItem createMenuItem(String text) {
        JMenuItem menuItem = new JMenuItem(text);
        menuItem.setBackground(new Color(220, 220, 220));
        menuItem.setForeground(Color.BLACK);
        return menuItem;
    }

    private void setLookAndFeel(String theme) {
        try {
            switch (theme) {
                case "Metal":
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                    break;
                case "Nimbus":
                    UIManager.setLookAndFeel(new NimbusLookAndFeel());
                    break;
                case "Synth":
                    UIManager.setLookAndFeel(new SynthLookAndFeel());
                    break;
                case "Windows":
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
                case "Motif":
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                    break;
                default:
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    break;
            }
            setUIFont(new Font("Arial", Font.PLAIN, 25));
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            logger.logError("Error setting look and feel: " + e.getMessage());
        }
    }

    private void setUIFont(Font f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    private void copyToClipboard(String text) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
        logger.logSuccess("Result copied to clipboard: " + text);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}