package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Lv1 extends Entity {

    public static final String objName = "nature_sword";

    public OBJ_Sword_Lv1(GamePanel gp) {
        super(gp);
        swordLevel = 1;
        type = type_sword;
        name = objName;
        eWidth = gp.tileSize;
        eHeight = gp.tileSize;
        down1 = setup("/objects/sword_lv1", gp.tileSize, gp.tileSize);
        attackvalue = 10;
        attackArea.width = 40;
        attackArea.height = 40;

        description = localize(
            "[" + name + "]\nThiên nhiên là nguồn\nsức mạnh đáng sợ\nnhất.",
            "[" + name + "]\nNature is the most\nrelentless power of\nall."
        );

        price = 50;
        knockBackPower = 3;
        motion1_duration = 5;
        motion2_duration = 25;
    }
    
    @Override
    public void rebuildText() {
    	description = localize(
                "[" + name + "]\nThiên nhiên là nguồn\nsức mạnh đáng sợ\nnhất.",
                "[" + name + "]\nNature is the most\nrelentless power of\nall."
            );
	}
}
