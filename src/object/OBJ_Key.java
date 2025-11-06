package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    public static final String objName = "key";
    GamePanel gp;

    public OBJ_Key(GamePanel gp) {
        super(gp);
        this.gp = gp;
        eWidth = gp.tileSize;
        eHeight = gp.tileSize;
        type = type_consumable;
        name = objName;
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize);
        description = localize(
            "[" + name + "]\nDùng để mở cổng.",
            "[" + name + "]\nUsed to unlock gates."
        );
        price = 20;
        stackable = true;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = localize(
            "Bạn đã dùng " + name + " và mở cửa thành công.",
            "You used the " + name + " and unlocked the door."
        );
        dialogues[1][0] = localize(
            "Hãy đến trước cửa và sử dụng!",
            "Stand before a door to use it!"
        );
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.dialogueState;
        int objIndex = getDetected(entity, gp.obj, "door");

        if (objIndex != 999) {
            startDialogue(this, 0);
            gp.playSe(3);
            gp.obj[gp.currentMap][objIndex] = null;
            return true;
        } else {
            startDialogue(this, 1);
            return false;
        }
    }
}
