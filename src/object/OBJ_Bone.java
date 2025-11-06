package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bone extends Entity {

    public static final String objName = "bone";
    GamePanel gp;

    public OBJ_Bone(GamePanel gp) {
        super(gp);
        this.gp = gp;

        eWidth = gp.tileSize;
        eHeight = gp.tileSize;
        type = type_consumable;
        name = objName;
        down1 = setup("/objects/bone", gp.tileSize, gp.tileSize);
        price = 20;
        stackable = true;

        rebuildText(); // <-- lấy đúng VI/EN ngay lúc spawn
    }

    @Override
    public void rebuildText() {
        description = localize(
            "[" + name + "]\nDùng để mở cổng tới\nchỗ Rồng.",
            "[" + name + "]\nUsed to unlock the gate\nto the Dragon’s lair."
        );
    }
}
