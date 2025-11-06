package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Armor_Lv2 extends Entity {

    public static final String objName = "freeze_armor";

    public OBJ_Armor_Lv2(GamePanel gp) {
        super(gp);
        swordLevel = 2;
        type = type_armor;
        eWidth = gp.tileSize;
        eHeight = gp.tileSize;
        name = objName;
        down1 = setup("/objects/armor_lv2", gp.tileSize, gp.tileSize);
        defenseValue = 20;
        description = localize(
            "[" + name + "]\nCái lạnh ngàn năm\ncũng vô dụng.",
            "[" + name + "]\nEven the eternal frost\ncannot harm it."
        );
        price = 100;
    }
    
    @Override
    public void rebuildText() {
    	description = localize(
                "[" + name + "]\nCái lạnh ngàn năm\ncũng vô dụng.",
                "[" + name + "]\nEven the eternal frost\ncannot harm it."
            );
	}
}
