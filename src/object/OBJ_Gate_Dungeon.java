package object;

import data.Progress;
import entity.Entity;
import main.GamePanel;

public class OBJ_Gate_Dungeon extends Entity {

    public static final String objName = "gate_dungeon";
    GamePanel gp;

    public OBJ_Gate_Dungeon(GamePanel gp) {
        super(gp);
        this.gp = gp;
        eWidth = gp.tileSize * 3;
        eHeight = gp.tileSize * 4 + 24;
        type = type_obstacle;
        name = objName;
        image = setup("/objects/gate_dungeon", eWidth, eHeight);
        down1 = image;
        collision = true;
        setDialogue();
        setDefaultSolidArea(24, 0, 96, 96, 0, 0);
    }
    
    @Override
    public void setDialogue() {
        dialogues[0][0] = localize(
            "Ryujin: Hahaha, lấy được đầu của ta trước đi rồi hẵng\ntính chuyện đi tiếp, hỡi tên con người yếu đuối đáng thương kia ơi.",
            "Ryujin: Hahaha! Take my head first before dreaming\nof moving forward, you pitiful human!"
        );
        dialogues[1][0] = localize(
            "Hahaha, khá khen cho nhà ngươi khi có thể hạ gục con rồng đó.",
            "Hahaha, impressive—you actually defeated that dragon."
        );
        dialogues[1][1] = localize(
            "Nhưng cho ngươi biết một điều rằng, ngài ấy có thể làm điều đó chỉ với một chiêu trong khi ngươi phải chật vật nãy giờ.",
            "But know this—my master could’ve done it\nwith a single strike while you struggled endlessly."
        );
        dialogues[1][2] = localize(
            "Mau tiến tới hầm ngục bên dưới đi, ngài ấy đang đợi ngươi đó hahaha.",
            "Go now to the dungeon below—he awaits you, hahaha!"
        );
    }

    public void interact() {
        if (Progress.dragonDefeated == false) {
            startDialogue(this, 1);
        } else {
            startDialogue(this, 0);
        }
    }
}
