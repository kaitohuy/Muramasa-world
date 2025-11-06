package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Lv3 extends Entity {

    public static final String objName = "fire_sword";

    public OBJ_Sword_Lv3(GamePanel gp) {
        super(gp);
        swordLevel = 3;
        type = type_sword;
        name = objName;
        eWidth = gp.tileSize;
        eHeight = gp.tileSize;
        down1 = setup("/objects/sword_lv3", gp.tileSize, gp.tileSize);
        attackvalue = 50;
        attackArea.width = 40;
        attackArea.height = 40;

        description = localize(
            "[" + name + "]\nLửa địa ngục thiêu\nrụi mọi sinh mệnh.",
            "[" + name + "]\nHellfire that sears\nall living things."
        );

        price = 200;
        knockBackPower = 3;
        motion1_duration = 5;
        motion2_duration = 25;
    }    
    @Override
    public void rebuildText() {
    	description = localize(
                "[" + name + "]\nLửa địa ngục thiêu\nrụi mọi sinh mệnh.",
                "[" + name + "]\nHellfire that sears\nall living things."
            );
	}
}
