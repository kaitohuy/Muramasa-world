package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueHeart extends Entity {

    public static final String objName = "blue heart";
    GamePanel gp;

    public OBJ_BlueHeart(GamePanel gp) {
        super(gp);
        this.gp = gp;
        eWidth = gp.tileSize;
        eHeight = gp.tileSize;
        type = type_pickupOnly;
        name = objName;
        down1 = setup("/objects/blueheart", eWidth, eHeight);
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = localize(
            "Bạn nhặt được một viên ngọc lam tuyệt đẹp.",
            "You pick up a beautiful blue gem."
        );
        dialogues[0][1] = localize(
            "Bạn tìm thấy Blue Heart, kho báu huyền thoại!",
            "You find the Blue Heart, the legendary treasure!"
        );
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.cutSceneState;
        gp.csManager.sceneNum = gp.csManager.ending;
        return true;
    }
    
	public void rebuildText() {
    	description = localize(
                "[" + name + "]\nLửa không có sợ lửa.",
                "[" + name + "]\nFire fears no fire."
            );
	}
}
