package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data.DatabaseConnection;

public class Main {
	
    public static JFrame window;

    public static void main(String[] args) {
        // === create dimension ===
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Muramasa");
        new Main().setIcon();

        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);

        gamepanel.config.loadConfig();
        if (gamepanel.fullScreenOn) {
            window.setUndecorated(true);
        }

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // === event ===
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (gamepanel.currentMap >= 1) {
                    gamepanel.gameState = gamepanel.pauseState;
                }

                int confirm = JOptionPane.showConfirmDialog(
                        window,
                        "Bạn có chắc chắn muốn thoát game?",
                        "Xác nhận thoát",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    if (gamepanel.currentMap >= 1) {
                        gamepanel.saveLoad.save();
                    }
                    System.exit(0);
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
                if (gamepanel.gameState != gamepanel.titleState) {
                    gamepanel.gameState = gamepanel.pauseState;
                } else {
                    gamepanel.gameState = gamepanel.titleState;
                }
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                if (gamepanel.currentMap >= 1) {
                    gamepanel.gameState = gamepanel.playState;
                } else {
                    gamepanel.gameState = gamepanel.titleState;
                }
            }
        });

        // === start GAME ===
        gamepanel.setupGame();
        gamepanel.startGameThread();
    }
	
    public void setIcon() {
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/player/king_down_1.png")));
            window.setIconImage(icon.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
