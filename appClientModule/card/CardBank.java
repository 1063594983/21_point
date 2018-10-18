package card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class CardBank {
	/*几副牌*/
	private int numOfDeck;
	
	/*牌库中的牌*/
	private ArrayList<Card> cardsRemain;
	
	/*场上的牌*/
	private ArrayList<Card> cardsOnStage;
	
	/*棋牌区的牌*/
	private ArrayList<Card> cardsDropped;
	
	/*构造函数*/
	public CardBank(int num) {
		this.numOfDeck = num;
		cardsRemain = new ArrayList<>();
		cardsOnStage = new ArrayList<>();
		cardsDropped = new ArrayList<>();
		
		for(int i = 0; i < num; i++) {
			Deck d = new Deck();
			cardsRemain.addAll(d.getCards());
		}
	}
	
	/*从牌库中抽一张牌
	 * 若牌库为空，将弃牌区中的牌洗入牌库
	 * */
	public Card pick() {
		int numOfCard = cardsRemain.size();
		if(numOfCard == 0) {
			refresh();
		}
		numOfCard = cardsRemain.size();
		Random rand = new Random();
		int index = rand.nextInt(numOfCard);
		Card c = cardsRemain.get(index);
		c.pick();
		cardsOnStage.add(c);
		cardsRemain.remove(index);
		return c;
	}
	
	/*洗牌，将弃牌区的牌洗入牌库*/
	public void refresh() {
		Iterator<Card> i = cardsDropped.iterator();
		while(i.hasNext()) {
			Card c = i.next();
			c.refresh();
		}
		
		cardsRemain.addAll(cardsDropped);
		cardsDropped.clear();
	}
	
	/*将场中的牌放入弃牌区*/
	public void finish() {
		Iterator<Card> i = cardsOnStage.iterator();
		while(i.hasNext()) {
			Card c = i.next();
			c.drop();
		}
		cardsDropped.addAll(cardsOnStage);
		cardsOnStage.clear();
	}
	
	/*获得牌库中的牌*/
	public ArrayList<Card> getCardsRemain() {
		return cardsRemain;
	}
	
	/*获得场上的牌*/
	public ArrayList<Card> getCardsOnStage() {
		return cardsOnStage;
	}

	/*获得弃牌区的牌*/
	public ArrayList<Card> getCardsDropped() {
		return cardsDropped;
	}
	
	
	
}
