package main;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    Container contentPane;

    public GameWindow(Container contentPane) {
        this.contentPane = contentPane;
        setTitle("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(contentPane);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void setContentPane(Container contentPane) {
        super.setContentPane(contentPane);
        pack();
    }
}
