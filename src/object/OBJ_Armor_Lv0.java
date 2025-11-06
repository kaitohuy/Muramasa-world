package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Armor_Lv0 extends Entity{

	public static final String objName = "basic_armor";
	
	public OBJ_Armor_Lv0(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		
		type = type_armor;
		eWidth = gp.tileSize;
		eHeight = gp.tileSize;
		name = objName;
		down1 = setup("/objects/armor_lv0", gp.tileSize, gp.tileSize);
		defenseValue = 5;
		description = localize(
			    "[" + name + "]\nÁo rất phù hợp với\nnhà thám hiểm.",
			    "[" + name + "]\nA sturdy armor\nfor new adventurers."
			);
		price = 10;
	}

	@Override
	public void rebuildText() {
	    description = localize(
	        "[" + name + "]\nÁo rất phù hợp với\nnhà thám hiểm.",
	        "[" + name + "]\nA sturdy armor\nfor new adventurers."
	    );
	}
}
