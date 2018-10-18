package card;

public class Deck {
	private Card[] cards;
	
	/*¹¹Ôìº¯Êı*/
	public Deck() {
		cards = new Card[52];
		int count = 0;
		for(int i = 1; i <= 13; i++) {
			cards[count++] = new Card(i, Suit.CLUB);
			cards[count++] = new Card(i, Suit.SPADE);
			cards[count++] = new Card(i, Suit.HEART);
			cards[count++] = new Card(i, Suit.DIAMOND);
		}
	}
}
