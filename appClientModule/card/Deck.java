package card;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> cards;
	
	/*���캯��*/
	public Deck() {
		cards = new ArrayList<>();
		int count = 0;
		for(int i = 1; i <= 13; i++) {
			cards.add(new Card(i, Suit.CLUB));
			cards.add(new Card(i, Suit.SPADE));
			cards.add(new Card(i, Suit.HEART));
			cards.add(new Card(i, Suit.DIAMOND));
		}
	}
	
	/*���һ����*/
	public ArrayList<Card> getCards() {
		return this.cards;
	}
}
