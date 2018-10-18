package player;

import java.util.ArrayList;

public class Hand {
	private int bet;
	public ArrayList<Card> cards = new ArrayList<Card>();
	
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public void resetCard(Card card) {
		card.drop();
	}
}
