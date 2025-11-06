package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Lv2 extends Entity {

    public static final String objName = "blizzard_sword";

    public OBJ_Sword_Lv2(GamePanel gp) {
        super(gp);
        swordLevel = 2;
        type = type_sword;
        name = objName;
        eWidth = gp.tileSize;
        eHeight = gp.tileSize;
        down1 = setup("/objects/sword_lv2", gp.tileSize, gp.tileSize);
        attackvalue = 20;
        attackArea.width = 40;
        attackArea.height = 40;

        description = localize(
            "[" + name + "]\nHơi thở bão tuyết\nđóng băng cả linh\nhồn.",
            "[" + name + "]\nA blizzard's breath\nfreezes even the\nsoul."
        );

        price = 100;
        knockBackPower = 3;
        motion1_duration = 5;
        motion2_duration = 25;
    }
    
    @Override
    public void rebuildText() {
    	description = localize(
                "[" + name + "]\nHơi thở bão tuyết\nđóng băng cả linh\nhồn.",
                "[" + name + "]\nA blizzard's breath\nfreezes even the\nsoul."
            );
	}
}
