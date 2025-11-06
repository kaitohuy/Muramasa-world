package main;

import java.io.*;

public class Config {
    GamePanel gp;

    // Thêm ngôn ngữ, mặc định VI
    public Lang lang = Lang.VI;

    public Config(GamePanel gp) {
        this.gp = gp;
    }

    public void saveConfig() {
        try {
            File configFile = new File("config.txt");
            if (!configFile.exists()) {
                configFile.createNewFile();
            }

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(configFile))) {
                // 1) Full screen
                bw.write(gp.fullScreenOn ? "On" : "Off");
                bw.newLine();

                // 2) Music volume
                bw.write(String.valueOf(gp.music.VolumeScale));
                bw.newLine();

                // 3) SE volume
                bw.write(String.valueOf(gp.se.VolumeScale));
                bw.newLine();

                // 4) Language (mới): "vi" hoặc "en"
                bw.write(lang == Lang.EN ? "en" : "vi");
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            File configFile = new File("config.txt");
            if (!configFile.exists()) {
                System.out.println("config.txt not found, creating a new one");
                // tạo file mới với mặc định hiện tại (full screen Off, volumes hiện tại, lang VI)
                saveConfig();
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(configFile))) {
                String s;

                // 1) Fullscreen
                s = br.readLine();
                if (s != null) {
                    gp.fullScreenOn = "On".equalsIgnoreCase(s);
                }

                // 2) Music volume
                s = br.readLine();
                if (s != null) {
                    gp.music.VolumeScale = Integer.parseInt(s.trim());
                }

                // 3) SE volume
                s = br.readLine();
                if (s != null) {
                    gp.se.VolumeScale = Integer.parseInt(s.trim());
                }

                // 4) Language (có thể chưa có nếu file cũ)
                s = br.readLine();
                if (s == null) {
                    // File cũ 3 dòng -> mặc định VI
                    lang = Lang.VI;
                } else {
                    s = s.trim().toLowerCase();
                    lang = "en".equals(s) ? Lang.EN : Lang.VI;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Nếu lỗi parse, không để game crash: dùng mặc định an toàn
            if (lang == null) lang = Lang.VI;
        }
    }
}
