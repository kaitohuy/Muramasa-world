package enviroment;

import java.awt.*;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class Lighting {

    GamePanel gp;
    BufferedImage darknessFilter;
    public int dayCounter;
    public float filterAlpha = 0f;

    public final int day = 0;
    public final int dusk = 1;
    public final int night = 2;
    public final int dawn = 3;
    public int dayState = day;

    // Reuse palette to reduce GC
    private final Color[] gradColors = new Color[] {
        new Color(0,0,0.1f,0.10f), new Color(0,0,0.1f,0.42f),
        new Color(0,0,0.1f,0.52f), new Color(0,0,0.1f,0.61f),
        new Color(0,0,0.1f,0.69f), new Color(0,0,0.1f,0.76f),
        new Color(0,0,0.1f,0.82f), new Color(0,0,0.1f,0.87f),
        new Color(0,0,0.1f,0.88f), new Color(0,0,0.1f,0.89f),
        new Color(0,0,0.1f,0.90f), new Color(0,0,0.1f,0.91f)
    };
    private final float[] gradFractions = new float[] {
        0.10f, 0.40f, 0.50f, 0.60f, 0.65f, 0.70f,
        0.75f, 0.80f, 0.85f, 0.90f, 0.95f, 1.00f
    };

    // Throttle: chỉ refresh khi cần
    private int lastCenterX = Integer.MIN_VALUE;
    private int lastCenterY = Integer.MIN_VALUE;
    private float lastAlpha = Float.NaN;
    private static final int MOVE_EPS = 2; // đổi tâm >=2 px mới refresh

    public Lighting(GamePanel gp) {
        this.gp = gp;
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        setLightSource(); // vẽ lần đầu
    }

    /** Dựng lại buffer khi đổi kích thước/loại đèn. */
    public void setLightSource() {
        if (darknessFilter == null
                || darknessFilter.getWidth() != gp.screenWidth
                || darknessFilter.getHeight() != gp.screenHeight) {
            darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
            // Bắt buộc refresh lại vì buffer mới
            lastCenterX = Integer.MIN_VALUE;
            lastCenterY = Integer.MIN_VALUE;
            lastAlpha = Float.NaN;
        }
        // Vẽ một frame ban đầu
        forceRefreshLightMap();
    }

    /** Tính tâm đốm sáng theo VỊ TRÍ RENDER của player (đã xử lý mép map). */
    private Point computeLightCenterOnScreen() {
        int cx = gp.player.getScreenX() + gp.tileSize / 2;
        int cy = gp.player.getScreenY() + gp.tileSize / 2;
        // Clamp an toàn (tránh tạo gradient tâm ngoài màn hình)
        if (cx < 0) cx = 0;
        if (cy < 0) cy = 0;
        if (cx > gp.screenWidth)  cx = gp.screenWidth;
        if (cy > gp.screenHeight) cy = gp.screenHeight;
        return new Point(cx, cy);
    }

    /** Vẽ lại light map NGAY LẬP TỨC (không kiểm tra thay đổi). */
    private void forceRefreshLightMap() {
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();
        g2.setComposite(AlphaComposite.Src);
        g2.setColor(new Color(0,0,0,0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.SrcOver);

        if (gp.player.currentLight == null) {
            g2.setColor(new Color(0, 0, 0.1f, 0.90f));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        } else {
            Point p = computeLightCenterOnScreen();
            RadialGradientPaint gPaint = new RadialGradientPaint(
                p.x, p.y,
                gp.player.currentLight.lightRadus,
                gradFractions, gradColors
            );
            g2.setPaint(gPaint);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            lastCenterX = p.x;
            lastCenterY = p.y;
        }
        g2.dispose();
    }

    /** Chỉ refresh khi tâm thay đổi đáng kể hoặc alpha đổi. */
    private void refreshIfNeeded() {
        boolean need = false;

        // 1) Player đổi loại đèn/radius
        if (gp.player.lightUpdated) {
            gp.player.lightUpdated = false;
            need = true;
        }

        // 2) Tâm đốm sáng thay đổi
        Point p = computeLightCenterOnScreen();
        if (Math.abs(p.x - lastCenterX) >= MOVE_EPS || Math.abs(p.y - lastCenterY) >= MOVE_EPS) {
            need = true;
            lastCenterX = p.x;
            lastCenterY = p.y;
        }

        // 3) Alpha đổi mạnh (dawn/dusk)
        if (Float.isNaN(lastAlpha) || Math.abs(filterAlpha - lastAlpha) > 0.01f) {
            // Không cần vẽ lại light map chỉ vì alpha — alpha áp lên khi draw()
            // nhưng nếu muốn tinh chỉnh biên mịn vùng rìa có thể bỏ qua check này.
            lastAlpha = filterAlpha;
        }

        if (need) {
            forceRefreshLightMap();
        }
    }

    public void resetDay() {
        dayState = day;
        filterAlpha = 0f;
        lastAlpha = Float.NaN;
    }

    public void update() {
        // Chu kỳ ngày đêm giữ nguyên
        switch (dayState) {
            case day -> {
                dayCounter++;
                if (dayCounter > 6000) { dayState = dusk; dayCounter = 0; }
            }
            case dusk -> {
                filterAlpha += 0.001f;
                if (filterAlpha > 1f) { filterAlpha = 1f; dayState = night; }
            }
            case night -> {
                dayCounter++;
                if (dayCounter > 6000) { dayState = dawn; dayCounter = 0; }
            }
            case dawn -> {
                filterAlpha -= 0.001f;
                if (filterAlpha < 0f) { filterAlpha = 0f; dayState = day; }
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (gp.currentArea == gp.outside) {
            // Chỉ refresh khi cần (player di chuyển/đổi đèn)
            refreshIfNeeded();

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
            g2.drawImage(darknessFilter, 0, 0, null);
            g2.setComposite(AlphaComposite.SrcOver);
        }

        // Đồng hồ cũ
        String situation = switch (dayState) {
            case day -> "12:00";
            case dusk -> "18:00";
            case night -> "24:00";
            case dawn -> "06:00";
            default -> "";
        };
        g2.setColor(Color.white);
        g2.setFont(gp.ui.VT323.deriveFont(40f));
        gp.ui.drawSubWindow(gp.tileSize * 17 + 40, gp.tileSize * 10 + 18, gp.tileSize * 2, gp.tileSize + 12);
        g2.drawString(situation, gp.tileSize * 18, gp.tileSize * 11 + 12);
    }
}
