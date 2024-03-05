package de.informatik.utils;

import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.plaf.ColorUIResource;
import java.awt.Color;

public class CustomLookAndFeel extends BasicLookAndFeel {
    @Override
    public String getName() {
        return "Custom Look and Feel";
    }

    @Override
    public String getID() {
        return "CustomLookAndFeel";
    }

    @Override
    public String getDescription() {
        return "Custom Look and Feel Description";
    }

    @Override
    public boolean isNativeLookAndFeel() {
        return false;
    }

    @Override
    public boolean isSupportedLookAndFeel() {
        return true;
    }

    @Override
    protected void initClassDefaults(javax.swing.UIDefaults table) {
        super.initClassDefaults(table);
    }

    @Override
    protected void initComponentDefaults(javax.swing.UIDefaults table) {
        super.initComponentDefaults(table);
        table.put("Panel.background", new ColorUIResource(Color.DARK_GRAY));
        table.put("Panel.foreground", new ColorUIResource(Color.WHITE));
        table.put("Button.background", new ColorUIResource(Color.DARK_GRAY));
        table.put("Button.foreground", new ColorUIResource(Color.WHITE));
    }
}
