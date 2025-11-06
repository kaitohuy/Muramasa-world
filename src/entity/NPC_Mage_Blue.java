package entity;
import main.GamePanel;

public class NPC_Mage_Blue extends Entity{
	
	public NPC_Mage_Blue(GamePanel gp) {
		
		super(gp);
		name = "npc_mage_blue";
		type = type_npc;
		direction = "down";
		dialogueSet = 0;
		maxFrameAttack = 14;
		standing = true;
		getStandingImage();
		setDialogue();
		onPath= false;
		setDefaultSolidArea(0, 0, 0, 0, 0, 0);
	}
	
	private void getStandingImage() {
		for (int i = 0; i < maxFrameAttack; i++) {
	    	String tempPath = "/npc/mage_blue_" + i;
	    	attackDown[i] = setup(tempPath, gp.tileSize*3, gp.tileSize*3);
		}
	}

	public void setDialogue() {
		int i = 0;
		if (gp.config.lang == main.Lang.EN) {
		    i = 0;
		    dialogues[6][i] = "Kira: Hmm, I came here for my own purpose, not\nfor anyone else. One thing’s certain—his head\nwill rest in my hand!"; i++;

		    i = 0;
		    dialogues[0][i] = "Mage: Long time no see. I wonder who now dwells\nwithin you?"; i++;
		    dialogues[0][i] = "Mage: After that battle, this body still remembers\nthe terror of my demon lord’s power."; i++;
		    dialogues[0][i] = "Mage: Yet here you stand again, with a completely\ndifferent aura. I wonder if history will change\nthis time."; i++;
		    dialogues[0][i] = "Mage: I have existed here since the dawn of all\nthings. My duty is to serve the strongest—the\nking of this world."; i++;
		    dialogues[0][i] = "Mage: If you win, I shall become your servant and\nopen the gate to your world."; i++;
		    dialogues[0][i] = "Mage: I’ll tell you one thing—as a reward for\nreaching this place."; i++;
		    dialogues[0][i] = "Mage: It’s said he’s mastering condensed time\nmagic. If you’re hit, your body will be frozen\ncompletely."; i++;
		    dialogues[0][i] = "Mage: Don’t get too close, or you’ll end up like a\ndummy for his training."; i++;
		    dialogues[0][i] = "Mage: Let’s see if today the throne will change\nhands. Step forward and face the challenge!\nHahahahah..."; i++;
		} else {
			i = 0;
			dialogues[6][i] = "Kira: Hmm, tôi tới đây vì mục đích riêng của tôi, chứ\nkhông vì lợi ích của ai cả. Tôi chỉ biết chắc một điều\nrằng: cái đầu của hắn ta sẽ nằm trên tay tôi!";
			dialogues[0][i] = "Mage: Đã lâu không gặp, ta thắc mắc rằng bên trong ngươi\nhiện giờ là kẻ nào đây?"; i++;
			dialogues[0][i] = "Mage: Sau trận chiến ngày đó, cơ thể hắn ta đã khắc sâu\nnỗi sợ hãi sức mạnh của ngài ma vương đây rồi."; i++;
			dialogues[0][i] = "Mage: Vậy mà giờ đây ngươi quay lại chốn này với 1 dương\nkhí hoàn toàn khác, không biết lịch sử liệu có khác đi\nkhông đây."; i++;
			dialogues[0][i] = "Mage: Ta vốn ở nơi này từ khởi nguyên của vạn vật, sứ\nmệnh của ta là phục vụ kẻ mạnh nhất-vua của thế giới này."; i++;
			dialogues[0][i] = "Mage: Nếu chiến thắng, ta sẽ trở thành đầy tớ và mở cổng\nđến thế giới của ngươi."; i++;
			dialogues[0][i] = "Mage: Ta sẽ tiết lộ cho ngươi 1 chuyện, coi như là phần\nthưởng vì đã tới được đây."; i++;
			dialogues[0][i] = "Mage: Nghe nói ngài ấy đang tu luyện thêm ma pháp cô đọng\nthời gian, nếu dính chiêu đó, cơ thể ngươi sẽ hoàn toàn\nbất động."; i++;
			dialogues[0][i] = "Mage: Nhớ là không được tiến tới quá gần, nếu không ngươi\nsẽ trông như cái bù nhìn cho ngài ấy tập luyện vậy."; i++;
			dialogues[0][i] = "Mage: Để xem liệu ngày hôm nay, ngôi vương có đổi chủ ?\nBước lên trên và chinh phục thử thách này đi !\nhahahahah........"; i++;
		}	
	}

	public void speak() {
	
		startDialogue(this, dialogueSet);
	}
	
}
