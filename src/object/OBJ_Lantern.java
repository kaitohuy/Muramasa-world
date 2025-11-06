package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {

    public static final String objName = "lantern";

    public OBJ_Lantern(GamePanel gp) {
        super(gp);
        eWidth = gp.tileSize;
        eHeight = gp.tileSize;
        type = type_light;
        name = objName;
        down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize);
        description = localize(
            "[lantern]\nÁnh dương tỏa sáng soi lối nhân gian.",
            "[lantern]\nThe light of dawn\nilluminates mankind’s\npath."
        );
        price = 50;
        lightRadus = 350;
    }
    
    @Override
    public void rebuildText() {
    	description = localize(
                "[lantern]\nÁnh dương tỏa sáng soi\nlối nhân gian.",
                "[lantern]\nThe light of dawn\nilluminates mankind’s\npath."
            );
	}
}
