package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Armor_Lv1 extends Entity {

    public static final String objName = "iron_armor";

    public OBJ_Armor_Lv1(GamePanel gp) {
        super(gp);
        swordLevel = 1;
        type = type_armor;
        eWidth = gp.tileSize;
        eHeight = gp.tileSize;
        name = objName;
        down1 = setup("/objects/armor_lv1", gp.tileSize, gp.tileSize);
        defenseValue = 10;
        description = localize(
            "[" + name + "]\nKhông một thanh gươm\nnào có thể đả đụng đến.",
            "[" + name + "]\nNo sword can pierce\nthrough this armor."
        );
        price = 50;
    }
    @Override
	public void rebuildText() {
		description = localize(
	            "[" + name + "]\nKhông một thanh gươm\nnào có thể đả đụng đến.",
	            "[" + name + "]\nNo sword can pierce\nthrough this armor."
	        );
	}
}
