package monster;

import data.Progress;
import entity.Monster;
import main.GamePanel;
import object.OBJ_Fire_Storm;

public class MON_Dragon extends Monster{

	public static final String monName  ="ryujin";
	
	GamePanel gp;
	
	public MON_Dragon(GamePanel gp) {
		super(gp);
		
		this.gp = gp;
		
		name = monName;
		type = type_crep;
		sleep = true;
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 5000;
		life = maxLife;
		name = monName;
		attack = 200;
		defense = 20;
		exp = 50000;
		knockBackPower = 5;
		maxFrameAttack = 2;
		frameDelay = 8;
		projectile = new OBJ_Fire_Storm(gp);
		setDefaultSolidArea(96, 96, 96, 96, 64, 48);
		
		dialogueSet = 0;
		setDialogue();
		getImage();
	}
	
	public void getImage() {
		
		if(inRage == false) {
			up1 = setup("/monster/dragon_attack_up_0", gp.tileSize*6, gp.tileSize*6);
			up2 = setup("/monster/dragon_attack_up_1", gp.tileSize*6 , gp.tileSize*6);
			down1 = setup("/monster/dragon_attack_down_0", gp.tileSize*6, gp.tileSize*6);
			down2 = setup("/monster/dragon_attack_down_1", gp.tileSize*6, gp.tileSize*6);
			left1 = setup("/monster/dragon_attack_left_0", gp.tileSize*6, gp.tileSize*6);
			left2 = setup("/monster/dragon_attack_left_1", gp.tileSize*6, gp.tileSize*6);
			right1 = setup("/monster/dragon_attack_right_0", gp.tileSize*6, gp.tileSize*6);
			right2 = setup("/monster/dragon_attack_right_1", gp.tileSize*6, gp.tileSize*6);
		}else {
			up1 = setup("/monster/dragon_phase2_attack_up_0", gp.tileSize*6, gp.tileSize*6);
			up2 = setup("/monster/dragon_phase2_attack_up_1", gp.tileSize*6 , gp.tileSize*6);
			down1 = setup("/monster/dragon_phase2_attack_down_0", gp.tileSize*6, gp.tileSize*6);
			down2 = setup("/monster/dragon_phase2_attack_down_1", gp.tileSize*6, gp.tileSize*6);
			left1 = setup("/monster/dragon_phase2_attack_left_0", gp.tileSize*6, gp.tileSize*6);
			left2 = setup("/monster/dragon_phase2_attack_left_1", gp.tileSize*6, gp.tileSize*6);
			right1 = setup("/monster/dragon_phase2_attack_right_0", gp.tileSize*6, gp.tileSize*6);
			right2 = setup("/monster/dragon_phase2_attack_right_1", gp.tileSize*6, gp.tileSize*6);
		}
		
	}

	public void setDialogue() {
	    int i = 0;
	
	    // --- Phase 1: Khi Ryujin còn bị điều khiển ---
	    dialogues[0][i] = localize(
	        "Ryujin: Đám con người các ngươi thật cứng đầu, dù ta\ncó giết bao nhiêu tên đi nữa thì chúng vẫn lũ lượt\nkéo đến.",
	        "Ryujin: You humans are stubborn.\nNo matter how many I slay,\nmore keep coming."
	    ); i++;
	
	    dialogues[0][i] = localize(
	        "Ryujin: Ta quá chán với lũ yếu đuối rồi, lại đây, ta\nsẽ cho ngươi biết thế nào là địa ngục thực sự.",
	        "Ryujin: I’ve grown tired of the weak.\nCome, I’ll show you what true\nhell feels like!"
	    ); i++;
	
	    // --- Phase 2: Sau khi được giải thoát ---
	    i = 0;
	    dialogues[1][i] = localize(
	        "Ryujin: Cảm ơn cậu, người chiến hữu năm xưa của tôi,\ngiờ đây tôi đã có thể lấy lại ý thức của bản thân.",
	        "Ryujin: Thank you, my comrade from\nthe old days. I’ve regained my\nown consciousness at last."
	    ); i++;
	
	    dialogues[1][i] = localize(
	        "Ryujin: Nhưng cậu biết không, thân xác này đã thuộc về\nhắn ta mất rồi.",
	        "Ryujin: But you see... this body no\nlonger belongs to me."
	    ); i++;
	
	    dialogues[1][i] = localize(
	        "Ryujin: Vào cái ngày mà chúng ta bại trận, để sống sót\ntôi đã lập một khế ước với hắn, và trở thành kẻ\ngác cổng hỏa ngục ở đây.",
	        "Ryujin: The day we fell, I made a pact\nwith him to survive — and became\nthe gatekeeper of this inferno."
	    ); i++;
	
	    dialogues[1][i] = localize(
	        "Ryujin: Mặc dù lấy lại được ý thức, nhưng cơ thể này\nvẫn do hắn kiểm soát.",
	        "Ryujin: Though my mind is free,\nthis body remains under his\ncontrol."
	    ); i++;
	
	    dialogues[1][i] = localize(
	        "Ryujin: Hắn cố tình để chúng ta nói chuyện nhằm khiến\ncậu nương tay với tôi. Nhưng xin cậu đừng bận tâm,\nsống như thế này chẳng thà chết còn hơn.",
	        "Ryujin: He lets us talk so you’d\nhesitate to strike. But don’t —\nI’d rather die than live like this."
	    ); i++;
	
	    dialogues[1][i] = localize(
	        "Ryujin: Lẽ ra tôi nên chết cùng với cậu vào ngày hôm đó.\nBây giờ tôi đã chuẩn bị tâm lý để chết đi một lần nữa rồi.",
	        "Ryujin: I should’ve died beside you\nthat day. I’m ready to fall\nonce more."
	    ); i++;
	
	    dialogues[1][i] = localize(
	        "Ryujin: Ý thức của tôi sắp biến mất rồi, tôi tin tưởng\nở cậu - người chiến hữu tuyệt vời nhất của tôi!",
	        "Ryujin: My mind is fading... I trust\nyou, my greatest comrade!"
	    ); i++;
	}

	
	public void setAction() {
		
		if(inRage == false && life < maxLife/2) {
			inRage = true;
			speak();
			getImage();			
			defaultSpeed++;
			speed = defaultSpeed;
			attack += 20;
			defense += 10;
			projectile.attack = 220;
		}
		
		if(getTileDistance(gp.player) < 10) {
			moveTowardPlayer(30);
		} 
		else {	
			getRandomDirection(60);
		}
		//check if it shoots a projectile
		checkShootOtNot(30, 30);
	}
	
	public void damageReaction() {
		
		actionLockCounter = 0;
		onPath = true;
	}

	public void speak() {
		
		if(inRage == true) {
			startDialogue(this, 1);
		}else {
			startDialogue(this, 0);
		}
	}

	public void checkDrop() {
		
		gp.dragonBattleOn = false;
		Progress.dragonDefeated = true;
		
		gp.stopMusic();
		gp.playMusic(19);
	}
}
