package main;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        Game game = new Game();
        JFrame.setDefaultLookAndFeelDecorated(true);
        game.getWindow().getRootPane().putClientProperty("JRootPane.titleBarBackground", Color.BLACK);
        game.getWindow().getRootPane().putClientProperty("JRootPane.titleBarForeground", Color.WHITE);
    }
}
