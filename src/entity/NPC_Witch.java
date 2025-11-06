package entity;

import main.GamePanel;
import object.OBJ_Summon_Book;

public class NPC_Witch extends Entity{
	
	private boolean giveBook = false;
	
	public NPC_Witch(GamePanel gp) {
		
		super(gp);
		name = "npc witch";
		type = type_npc;
		eWidth = gp.tileSize*3;
		eHeight = gp.tileSize*3;
		direction = "left";
		dialogueSet = 0;
		maxFrameAttack = 9;
		frameDelay = 8;
		standing = true;
		getStandingImage();
		setDialogue();
		setDefaultSolidArea(0, 0, eWidth, eHeight, 0, 0);
	}
	
	private void getStandingImage() {
		for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/npc/witch_" + i;
	    	attackLeft[i] = setup(tempPath, eWidth, eHeight);
		}
	}

	public void setDialogue() {
		if (gp.config.lang == main.Lang.EN) {
		    dialogues[0][0] = "Witch: It’s a blessing that you managed to\nreach this place. I’m the apprentice of the one\nwho brought you to this world.";
		    dialogues[0][1] = "Witch: I was told to help you gain more power.\nI’ll give you a magic book—it holds the way to\nsummon your own allies.";
		    dialogues[0][2] = "Witch: Open your inventory to check and use it.\nI hope you achieve the purpose you seek.";
		    dialogues[1][0] = "Witch: I’m sorry, but I can’t help you any\nfurther. May fortune guide you!";

		} else {
			dialogues[0][0] = "Witch: Thật may mắn vì cậu đã có thể tới được đây.\nTôi là học trò của người đã đưa cậu tới thế giới này.";
			dialogues[0][1] = "Witch: Tôi được dặn phải giúp cậu có thêm sức mạnh.\nTôi sẽ tặng cậu cuốn sách ma thuật,trong đó sẽ có\ncách để cậu có thêm đồng minh cho mình.";
			dialogues[0][2] = "Witch: Hãy mở kho đồ để kiểm tra và sử dụng nó. Mong\nrằng cậu sẽ hoàn thành được mục đích của mình.";
			dialogues[1][0] = "Witch: Xin lỗi, tôi không thể giúp cậu hơn được nữa.\nChúc cậu may mắn!";
		}
	}

	public void speak() {
	
		if(giveBook == true) {
			startDialogue(this, 1);
		}
		
		if(giveBook == false) {
			startDialogue(this, 0);
			Entity summonBook = new OBJ_Summon_Book(gp);
			gp.player.inventory.add(summonBook);
			giveBook = true;
		}
	}
	
}
