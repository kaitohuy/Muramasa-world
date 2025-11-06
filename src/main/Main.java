package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import data.DatabaseConnection;

public class Main {

    public static JFrame window;

    public static void main(String[] args) {

        DatabaseConnection db = new DatabaseConnection();
        Path dbPath = Paths.get("gamedata.db"); 

        try {
            if (Files.notExists(dbPath)) {
                System.out.println("DB not found, creating...");
                db.setupDatabase();
            } else {
                System.out.println("DB already exists, skipping setup.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try { db.setupDatabase(); } catch (Exception ignored) {}
        }

        // === TẠO CỬA SỔ GAME ===
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

        // === SỰ KIỆN ĐÓNG / THU NHỎ / PHỤC HỒI ===
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

        // === KHỞI ĐỘNG GAME ===
        gamepanel.setupGame();
        gamepanel.startGameThread();
    }

    public void setIcon() {
        try (InputStream is = getClass().getResourceAsStream("/player/king_down_1.png")) {
            if (is != null) {
                ImageIcon icon = new ImageIcon(ImageIO.read(is));
                window.setIconImage(icon.getImage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
