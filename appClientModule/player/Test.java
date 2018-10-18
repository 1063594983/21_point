package player;

import card.Card;
import card.CardBank;

public class Test {
	public static void main(String[] args) {
		Player p = new Player("1", 200);
		Hand h = new Hand(20);
		Dealer d = new Dealer();
		p.addHand(h);
		CardBank cb = new CardBank(1);
		for(int i = 0; i < 5; i++) {
			Card c = cb.pick();
			h.addCard(c);
			System.out.println(p.getPlayername() + " " + c.getName() + " " + h.getTotalPoint()); 
		}
		for(int i = 0; i < 5; i++) {
			Card c = cb.pick();
			d.addCard(c);
			System.out.println("×¯¼Ò " + c.getName() + " " + d.getTotalPoint());
		}
		
		if(d.getTotalPoint() >= h.getTotalPoint()) {
			System.out.println(p.getMoney());
			p.changeMoney(-h.getBet());
			System.out.println(p.getMoney());
		} else {
			System.out.println(p.getMoney());
			p.changeMoney(h.getBet());
			System.out.println(p.getMoney());
		}
			
	}
}
