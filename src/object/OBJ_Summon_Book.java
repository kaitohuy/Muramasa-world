package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Summon_Book extends Entity {

    public static final String objName = "summon book";
    GamePanel gp;

    public OBJ_Summon_Book(GamePanel gp) {
        super(gp);
        this.gp = gp;

        eWidth = gp.tileSize;
        eHeight = gp.tileSize;
        type = type_book;
        name = objName;
        down1 = setup("/objects/summon_book", gp.tileSize, gp.tileSize);
        defenseValue = 1;

        description = localize(
            "[" + name + "]\nHướng dẫn triệu hồi\nPhoenix",
            "[" + name + "]\nGuide to summon\nPhoenix"
        );

        price = 5;
        stackable = true;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0][0] = localize(
            "Bạn đã học cách gọi Phoenix.\nNhấn 'O' hoặc '5' để\ntriệu hồi.",
            "You learned to summon Phoenix.\nPress 'O' or '5' to\nsummon."
        );
    }

    public boolean use(Entity entity) {
        gp.gameState = gp.playState;
        gp.player.canUseSkill3 = true;
        startDialogue(this, 0);
        return true;
    }
    
    @Override
    public void rebuildText() {
    	description = localize(
                "[" + name + "]\nHướng dẫn triệu hồi\nPhoenix",
                "[" + name + "]\nGuide to summon\nPhoenix"
            );
	}
}
