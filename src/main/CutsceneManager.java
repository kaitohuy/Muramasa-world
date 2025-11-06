package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import entity.PlayerDummy;
import monster.MON_Dragon;
import monster.MON_Ishigami;
import object.OBJ_Black_Rock;

public class CutsceneManager {

    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;
    int counter = 0;
    float alpha = 0f;
    int y;

    //Scene Number
    public final int NA = 0;
    public final int ryujin = 1;
    public final int ishigami = 2;
    public final int ending = 3;

    // ===== i18n helper =====
    private boolean isVi() {
    	return gp != null && gp.config != null
    	        && "vi".equalsIgnoreCase(String.valueOf(gp.config.lang));
    }
    private String L(String vi, String en) { return isVi() ? vi : en; }

    // ===== Credits (localized) =====
    private String endCreditVi, endCreditEn;

    public CutsceneManager(GamePanel gp) {
        this.gp = gp;

        endCreditVi =
                "PHÁT TRIỂN BỞI\n" +
                "NGUYEN DOAN HUY\n" +
                "KAITOHUY STUDIO\n" +
                "\n" +
                "LẬP TRÌNH CHÍNH\n" +
                "NGUYEN DOAN HUY\n" +
                "\n" +
                "QUẢN LÝ DỰ ÁN\n" +
                "NGUYEN DOAN HUY\n" +
                "\n" +
                "THIẾT KẾ ĐỒ HỌA\n" +
                "NGUYEN DOAN HUY\n" +
                "NGUYEN TRONG DUC\n" +
                "\n" +
                "ÂM THANH & ÂM NHẠC\n" +
                "NGUYEN DOAN HUY\n" +
                "\n" +
                "THÔNG SỐ KỸ THUẬT\n" +
                "NGUYEN DOAN HUY\n" +
                "\n" +
                "KIỂM THỬ\n" +
                "NGUYEN DOAN HUY\n" +
                "NGUYEN DOAN HUNG\n" +
                "\n" +
                "BÁO CÁO & HỖ TRỢ\n" +
                "NGUYEN DOAN HUY\n" +
                "\n\n\n\n\n" +
                "Trân trọng cảm ơn mọi người\n" +
                "\n\n\n" +
                "SEASON 2:\n" +
                "CON ĐƯỜNG TRỞ VỀ THẾ GIỚI LOÀI NGƯỜI\n" +
                "SẮP RA MẮT....\n" +
                "\n\n\n" +
                "CẢM ƠN VÌ ĐÃ CHƠI!";

        endCreditEn =
                "DEVELOPED BY\n" +
                "NGUYEN DOAN HUY\n" +
                "KAITOHUY STUDIO\n" +
                "\n" +
                "LEAD PROGRAMMER\n" +
                "NGUYEN DOAN HUY\n" +
                "\n" +
                "PROJECT MANAGER\n" +
                "NGUYEN DOAN HUY\n" +
                "\n" +
                "GRAPHICS DESIGNER\n" +
                "NGUYEN DOAN HUY\n" +
                "NGUYEN TRONG DUC\n" +
                "\n" +
                "SOUND & MUSIC\n" +
                "NGUYEN DOAN HUY\n" +
                "\n" +
                "TECHNICAL SPECIFICATION\n" +
                "NGUYEN DOAN HUY\n" +
                "\n" +
                "TESTER\n" +
                "NGUYEN DOAN HUY\n" +
                "NGUYEN DOAN HUNG\n" +
                "\n" +
                "REPORTING SPECIALIST\n" +
                "NGUYEN DOAN HUY\n" +
                "\n\n\n\n\n" +
                "Special thanks to everyone\n" +
                "\n\n\n" +
                "SEASON 2:\n" +
                "ROAD TO THE HUMAN WORLD\n" +
                "IS COMING SOON....\n" +
                "\n\n\n" +
                "THANKS FOR PLAYING!";
//      (Không cần gộp endCredit nữa; dùng L() khi vẽ)
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        switch (sceneNum) {
            case ryujin -> scene_ryujin();
            case ishigami -> scene_ishigami();
            case ending -> scene_ending();
        }
    }

    public void scene_ryujin() {

        if (scenePhase == 0) {
            gp.playSe(21);
            gp.dragonBattleOn = true;

            // Dummy
            for (int i = 0; i < gp.npc[gp.currentMap].length; i++) {
                if (gp.npc[gp.currentMap][i] == null) {
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            gp.player.drawing = false;
            scenePhase++;
        }

        if (scenePhase == 1) {
            gp.player.worldY -= 4;
            if (gp.player.worldY < gp.tileSize * 10) {
                scenePhase++;
            }
        }

        if (scenePhase == 2) {
            for (int i = 0; i < gp.monster[gp.currentMap].length; i++) {
                if (gp.monster[gp.currentMap][i] != null &&
                        gp.monster[gp.currentMap][i].name.equals(MON_Dragon.monName)) {
                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }

        if (scenePhase == 3) {
            gp.ui.drawDialogueScreen();
        }

        if (scenePhase == 4) {
            for (int i = 0; i < gp.npc[gp.currentMap].length; i++) {
                if (gp.npc[gp.currentMap][i] != null &&
                        gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                    gp.player.direction = gp.npc[gp.currentMap][i].direction;
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }
            gp.player.drawing = true;

            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;

            gp.stopMusic();
            gp.playMusic(22);
        }
    }

    public void scene_ishigami() {

        if (scenePhase == 0) {
            gp.ishigamiBattleOn = true;

            // Shut door
            for (int i = 0; i < gp.obj[gp.currentMap].length; i++) {
                if (gp.obj[gp.currentMap][i] == null) {
                    gp.obj[gp.currentMap][i] = new OBJ_Black_Rock(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize * 41;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize * 21;
                    gp.obj[gp.currentMap][i].temp = true;
                    gp.playSe(21);
                    break;
                }
            }

            // Dummy
            for (int i = 0; i < gp.npc[gp.currentMap].length; i++) {
                if (gp.npc[gp.currentMap][i] == null) {
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            gp.player.drawing = false;
            scenePhase++;
        }

        if (scenePhase == 1) {
            gp.player.worldY -= 6;
            if (gp.player.worldY < gp.tileSize * 10) scenePhase++;
        }

        if (scenePhase == 2) {
            gp.player.worldX -= 6;
            if (gp.player.worldX < gp.tileSize * 10) scenePhase++;
        }

        if (scenePhase == 3) {
            for (int i = 0; i < gp.monster[gp.currentMap].length; i++) {
                if (gp.monster[gp.currentMap][i] != null &&
                        gp.monster[gp.currentMap][i].name.equals(MON_Ishigami.monName)) {
                    gp.monster[gp.currentMap][i].sleep = false;
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }

        if (scenePhase == 4) {
            gp.ui.drawDialogueScreen();
        }

        if (scenePhase == 5) {
            for (int i = 0; i < gp.npc[gp.currentMap].length; i++) {
                if (gp.npc[gp.currentMap][i] != null &&
                        gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName)) {
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                    gp.player.direction = gp.npc[gp.currentMap][i].direction;
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }
            gp.player.drawing = true;

            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;

            gp.stopMusic();
            gp.playMusic(19);
        }
    }

    public void scene_ending() {

        if (scenePhase == 0) {
            gp.stopMusic();
            gp.playMusic(26);
            if (counterReached(60)) scenePhase++;
        }

        if (scenePhase == 1) {
            alpha += 0.005f;
            if (alpha > 1f) alpha = 1f;
            drawBlackBackground(alpha);
            if (alpha == 1f) { alpha = 0; scenePhase++; }
        }

        if (scenePhase == 2) {
            drawBlackBackground(1f);
            alpha += 0.005f;
            if (alpha > 1f) alpha = 1f;

            String vi =
                    "Sau trận chiến thượng đỉnh giữa Wirzard và Kira\n" +
                    "Kẻ thắng kẻ thua đã phân định, tân vương mới được thiết lập\n" +
                    "Nhưng câu chuyện chưa kết thúc ở đây\n" +
                    "Mọi thứ chỉ mới bắt đầu, câu chuyện về Kira còn tiếp diễn...\n";

            String en =
                    "After the climactic duel between Wirzard and Kira,\n" +
                    "the victor is crowned and a new king rises.\n" +
                    "But the tale does not end here.\n" +
                    "This is only the beginning—the story of Kira continues...\n";

            drawString(alpha, 38f, 200, L(vi, en), 70);

            if (counterReached(360)) { gp.playMusic(0); scenePhase++; }
        }

        if (scenePhase == 3) {
            drawBlackBackground(1f);
            drawString(1f, 120f, gp.screenHeight / 2, "MURAMASA", 40);
            if (counterReached(180)) scenePhase++;
        }

        if (scenePhase == 4) {
            drawBlackBackground(1f);
            y = gp.screenHeight / 2;
            drawString(1f, 38f, y, L(endCreditVi, endCreditEn), 40);
            if (counterReached(60)) scenePhase++;
        }

        if (scenePhase == 5) {
            drawBlackBackground(1f);
            y--;
            drawString(1f, 38f, y, L(endCreditVi, endCreditEn), 40);
            if (counterReached(1800)) scenePhase++;
        }

        if (scenePhase == 6) {
            gp.gameState = gp.titleState;
            gp.ui.titleScreenState = 0;
            gp.playMusic(26);
        }
    }

    public boolean counterReached(int target) {
        boolean ok = false;
        counter++;
        if (counter > target) { ok = true; counter = 0; }
        return ok;
    }

    public void drawBlackBackground(float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));
        for (String line : text.split("\n")) {
            int x = gp.ui.getXForCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
