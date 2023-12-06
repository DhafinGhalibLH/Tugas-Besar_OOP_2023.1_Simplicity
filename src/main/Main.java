package main;

import javax.swing.*;

public class Main {
    public static JFrame window;

    public void setIcon() {
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("logo/SimPlicity-tbg.png"));
        window.setIconImage(icon.getImage());
    }

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Sim-Plicity\n");

        new Main().setIcon();

        Panel panel = new Panel();
        window.add(panel);

        // panel.config.loadConfig();
        if (panel.isFullscreen == true) {
            window.setUndecorated(true);
        }

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.setup();
        panel.startGame();
    }
}
