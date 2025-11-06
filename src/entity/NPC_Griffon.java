package entity;

import main.GamePanel;

public class NPC_Griffon extends NPC{
	
	public NPC_Griffon(GamePanel gp) {
		
		super(gp);
		name = "griffon";
		direction = "down";
		speed = 2;
		
		setDefaultSolidArea(0, 0, 0, 0, 0 , 0);

		dialogueSet = 0;
		onPath = true;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		
		up1 = setup("/npc/Griffon_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/Griffon_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/Griffon_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/Griffon_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/Griffon_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/Griffon_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/Griffon_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/Griffon_right_2", gp.tileSize, gp.tileSize);
		
	}
	
	public void setDialogue() {
	    int i;

	    if (gp.config.lang == main.Lang.EN) {
	    	i = 0;
	        dialogues[0][i] = "Kira: You spent your life just to summon\nme—a nobody? While stronger ones exist?\nOld man, you chose poorly."; i++;
	        dialogues[0][i] = "Griffon: Do not speak that way! He has always\nbeen the one our people deeply respect."; i++;
	        dialogues[0][i] = "Griffon: From now on, I'll teach you how to use\nthat body in the best way."; i++;
	        dialogues[0][i] = "Griffon: First, take this armor and sword. Press\n'C' to check them."; i++;
	        dialogues[0][i] = "Griffon: To unequip or use items, move with\n'ASWD' or arrows, then press 'Enter' or 'J' to\nact."; i++;
	        dialogues[0][i] = "Griffon: To close the inventory, press 'Space' or\n'Esc' or 'C'."; i++;
	        dialogues[0][i] = "Griffon: To move, use 'ASWD' or the arrow keys."; i++;
	        dialogues[0][i] = "Griffon: Press 'J' or '1' to swing your sword."; i++;
	        dialogues[0][i] = "Griffon: Press 'U' or '4' to throw a shuriken,\nbut test it outside—no using it indoors."; i++;
	        dialogues[0][i] = "Griffon: I’ll be your eyes. Press 'X' for mini map\nand 'M' for full map. Press them again or\n'Space'/'ESC' to close."; i++;
	        dialogues[0][i] = "Griffon: For world settings, press 'Space'. To\nexit, press 'Space' or 'ESC'."; i++;
	        dialogues[0][i] = "Griffon: That’s how to use your body. Now go\noutside and slay those demons."; i++;
	        dialogues[0][i] = "Kira: Hmph, is that all? Prepare to witness\nmy rebirth!"; i++;

	        i = 0;
	        dialogues[1][i] = "Griffon: This is the prairie where we once\nlived."; i++;
	        dialogues[1][i] = "Griffon: Each area has a vile gate that blocks\nour path. Defeat the boss, take the key—\ngood luck."; i++;
	        dialogues[1][i] = "Kira: Time to test this new power. All of you—\nkneel before me!"; i++;

	        i = 0;
	        dialogues[2][i] = "Griffon: We've arrived at the most prosperous\nvillage of this world. As you see, only ice and\nsnow remain."; i++;
	        dialogues[2][i] = "Griffon: I heard one house still aids heroes like\nyou. Try to find it."; i++;
	        dialogues[2][i] = "Kira: I'd do that anyway. I'm sick of this bitter\ncold. I could use a warmer coat."; i++;

	        i = 0;
	        dialogues[3][i] = "Griffon: At last we're here. They say to reach\nhim, we must defeat two lieutenants first."; i++;
	        dialogues[3][i] = "Griffon: Each is stronger than the previous ice\ndemon. Be careful!"; i++;
	        dialogues[3][i] = "Kira: If that's all, there's nothing to fear. I\nkilled that thing and lived, didn't I?"; i++;
	        dialogues[3][i] = "Kira: Let me see how strong the monster you\nfear truly is. This will be quick!"; i++;

	        i = 0;
	        dialogues[4][i] = "Kira: Ryujin..."; i++;
	        dialogues[4][i] = "Kira: Huh? Who said that name? Is it this body's\nmemory, even though its will has faded?"; i++;
	        dialogues[4][i] = "Griffon: Yes. I heard that dragon once fought\nbeside you across the land."; i++;
	        dialogues[4][i] = "Griffon: But now he is our greatest fear. He is\nno longer your friend, Kira. End him and this\nwill all be over."; i++;
	        dialogues[4][i] = "Kira: Hmph. I never knew that dragon. I have no\nreason to show mercy."; i++;
	        dialogues[4][i] = "Kira: I said it from the start: anyone who blocks\nmy path—dies!"; i++;

	        i = 0;
	        dialogues[5][i] = "Griffon: This is my first time here. It seems\nabsent from the village records."; i++;
	        dialogues[5][i] = "Griffon: Perhaps none survived the dragon's trial,\nso this place was never known."; i++;
	        dialogues[5][i] = "Griffon: Be careful. I suspect traps nearby—stay\nalert, Kira!"; i++;
	        dialogues[5][i] = "Kira: Fine, fine. You talk too much. I'm not so\nfoolish as to rush in with no intel."; i++;
	        dialogues[5][i] = "Kira: Either way, I'll survive—just as I always\ndo. Let's move!"; i++;

	        i = 0;
	        dialogues[6][i] = "Griffon: I'm sorry I may not witness you bringing\npeace to your homeland."; i++;
	        dialogues[6][i] = "Griffon: You'll defeat him, won't you? I—no, we—\nbelieve in you."; i++;
	        dialogues[6][i] = "Kira: Hm. I'm here for my own purpose, not for\nanyone's sake. One thing is certain: his head\nwill rest in my hand!"; i++;

	    } else {
	    	i = 0;
			dialogues[0][i] = "Kira: Bỏ mạng chỉ để gọi ta, một kẻ vô danh ư? Trong khi\nbiết bao kẻ mạnh hơn ngoài kia. Có lẽ ông đã sai lầm rồi\nông già à."; i++;
			dialogues[0][i] = "Griffon: Không được nói như vậy! Ngài ấy vẫn luôn là\nngười được dân lòng tôn trọng."; i++;
			dialogues[0][i] = "Griffon: Từ giờ, ta sẽ hướng dẫn ngươi cách sử dựng cơ\nthể đó một cách tốt nhất."; i++;
			dialogues[0][i] = "Griffon: Đầu tiên ta sẽ tặng ngươi một áo giáp và một\nthanh gươm, hãy nhấn 'C' để kiểm tra."; i++;
			dialogues[0][i] = "Griffon: Để tháo đồ hay sử dụng đồ, sử dụng 'ASWD' hoặc\ncác phím mũi tên để di chuyển, nhấn 'Enter' hoặc 'J' để\nthao tác."; i++;
			dialogues[0][i] = "Griffon: Để thoát khỏi kho đồ, nhấn 'Space' hoặc 'Esc'\nhoặc 'C'."; i++;
			dialogues[0][i] = "Griffon: Để di chuyển, ngươi có thể sử dụng các phím\n'ASWD' hoặc các phím mũi tên."; i++;
			dialogues[0][i] = "Griffon: Phím 'J' hoặc '1' để vung gươm."; i++;
			dialogues[0][i] = "Griffon: Phím 'U' hoặc '4' để ném shuriken, nhưng mà\nmuốn thử thì ra ngoài, không được dùng trong nhà."; i++;
			dialogues[0][i] = "Griffon: Ta sẽ là con mắt của nhà ngươi, nhấn phím 'X'\nđể mở mini map, nhấn phím 'M' để xem full map, để thoát\nnhấn lại các phím đó hoặc phím 'Space', 'ESC'."; i++;
			dialogues[0][i] = "Griffon: Để tùy chỉnh cài đặt của thế giới này, nhấn\n'Space', để thoát thì nhấn 'Space' hoặc 'ESC'."; i++;
			dialogues[0][i] = "Griffon: Đó là cách để sử dụng cơ thể đó, bây giờ thì\nđi ra ngoài và tiêu diệt lũ ác quỷ đó đi."; i++;
			dialogues[0][i] = "Kira: Hmm, chỉ có vậy thôi sao, chuẩn bị chiêm ngưỡng\nsự tái sinh của ta đi!"; i++;
			
			i = 0;
			dialogues[1][i] = "Griffon: Đây là vùng thảo nguyên mà bọn ta từng sinh\nsống."; i++;
			dialogues[1][i] = "Griffon: Ở mỗi khu vực đều có cánh cổng ma quái ngăn\ncản ta đi tiếp. Hãy tiêu diệt tên đầu sỏ và lấy chìa\nkhóa, chúc ngươi may mắn."; i++;
			dialogues[1][i] = "Kira: Nào, đã đến lúc thử sức mạnh mới này rồi, tất cả\ncác ngươi hãy quy phục dưới chân ta!"; i++;
			
			i = 0;
			dialogues[2][i] = "Griffon: Chúng ta đã đặt chân đến ngôi làng thịnh vượng\nnhất thế giới này. Như cậu đã thấy, nơi đây chỉ còn lại\nbăng tuyết."; i++;
			dialogues[2][i] = "Griffon: Tôi nghe nói vẫn còn sót lại một ngôi nhà để\ngiúp đỡ các vị anh hùng như cậu. Hãy thử đi tìm ngôi nhà\nđó xem sao."; i++;
			dialogues[2][i] = "Kira: Không cần ngươi nói ta cũng sẽ làm, ta chán ngấy\ncái lạnh rét buốt này rồi. Có lẽ ta cần một chiếc áo ấm\nhơn."; i++;
			
			i = 0;
			dialogues[3][i] = "Griffon: Cuối cùng chúng ta đã đến được đây, nghe nói\nđể tới được chố hắn, ta phải hạ gục 2 tên cận thần."; i++;
			dialogues[3][i] = "Griffon: Mỗi tên trong số chúng đều mạnh hơn tên quỷ\nbănglần trước đó, hãy cẩn thận!"; i++;
			dialogues[3][i] = "Kira: Hừm, nếu chỉ như vậy thì không có gì đáng lo ngại cả\nDù sao thì ta đã giết nó mà vẫn giữ được mạng đó thôi."; i++;
			dialogues[3][i] = "Kira: Để ta xem thứ quái vật các ngươi run sợ mạnh tới\ncỡ nào. Sẽ xong nhanh thôi!."; i++;

			i= 0;
			dialogues[4][i] = "Kira: Ryujin...."; i++;
			dialogues[4][i] = "Kira: Hả, cái gì vậy, ai đã nói ra cái tên đó vậy?\nLà ký ức của thân xác này hay sao, dù ý thức đã mất?"; i++;
			dialogues[4][i] = "Griffon: Đúng vậy, tôi nghe nói ngày xưa con rồng đó\nđã cùng cậu chinh chiến khắp nơi."; i++;
			dialogues[4][i] = "Griffon: Nhưng giờ đây,hắn chính là nỗi khiếp sợ của\nchúng tôi. Đó không còn là bạn của cậu nữa rồi Kira à\nTiêu diệt hắn là mọi thứ sẽ kết thúc."; i++;
			dialogues[4][i] = "Kira: Hừ, ta với con rồng đó vốn không quen biết nhau\nKhông có lý do gì để ta nương ta cả."; i++;
			dialogues[4][i] = "Kira: Ta đã nói ngay từ đầu rồi. Bất cứ ai cản bước ta,\ngiết không tha!"; i++;
			
			i = 0;
			dialogues[5][i] = "Griffon: Đây là lần đầu tiên tôi tới đây, nơi này dường\nnhư không được lưu truyền trong làng."; i++;
			dialogues[5][i] = "Griffon: Có lẽ vì không ai đủ sức vượt qua ải con rồng\nđó nên nơi này không được biết đến."; i++;
			dialogues[5][i] = "Griffon: Hãy cẩn thận, tôi nghi ngờ rằng sẽ có bẫy hay\ngì đó xung quanh, cẩn thận không có thừa đâu Kira!"; i++;
			dialogues[5][i] = "Kira: Rồi rồi, ngươi thật nhiều lời quá đi, ta cũng\nkhông có chủ quan đến mức cứ thế dấn thân vào lòng địch\nkhi không có chút thông tin nào."; i++;
			dialogues[5][i] = "Kira: Mà dù gì ta cũng sẽ sống sót như cách ta đã làm\ntrước đây thôi, được rồi, đi nào!"; i++;
			
			i = 0;
			dialogues[6][i] = "Griffon: Tôi rất tiếc khi không thể chứng kiến cảnh cậu\nđem lại hòa bình cho quê nhà của mình."; i++;
			dialogues[6][i] = "Griffon: Cậu chắc chắn sẽ đánh bại tên đó đúng không?\ntôi, à không, chúng tôi tin ở cậu."; i++;
			dialogues[6][i] = "Kira: Hmm, tôi tới đây vì mục đích riêng của tôi, chứ\nkhông vì lợi ích của ai cả. Tôi chỉ biết chắc một điều\nrằng: cái đầu của hắn ta sẽ nằm trên tay tôi!"; i++;
	    }
	}


	public void setAction() {
		
		if(onPath == true) {
			
			int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
			int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
			
			if (goalCol - 1 >= 0) {
				goalCol -= 1;
			}
			if(goalRow - 1 >= 0) {
				goalRow -= 1;
			}
			searchPath(goalCol, goalRow);
		}
	}

	public void speak() {
		
		facePlayer();
		switch (gp.currentMap){
			case 0: {
				startDialogue(this, 0);
				break;
			}
			case 1: {
				startDialogue(this, 1);
				break;
			}
			case 2: {
				startDialogue(this, 2);
				break;
			}
			case 3: {
				if(gp.dragonBattleOn == true) {
					startDialogue(this, 4);
				}else {
					startDialogue(this, 3);
				}
				break;
			}
			case 4: {
				if(gp.ishigamiBattleOn == true) {
					dying = true;
					startDialogue(this, 6);
				}else {
					startDialogue(this, 5);
				}
				break;					
			}
		}
	}
}
