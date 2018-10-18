package player;

import java.util.ArrayList;

import card.Card;

public class Dealer {
	private int totalpoint;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	/*
	 * 获得总点数
	 */
	public int getTotolPoint() {
		return totalpoint;
	}
	
	/*
	 * 增加手中的牌（摸牌）
	 */
	public void addCard(Card card) {
		cards.add(card);
		totalpoint += card.getValue();
	}
	
	/*
	 * 一局游戏结束时将手中的牌放入弃牌池
	 */
	public void dropCard() {
		for(int i = 0; i < cards.size(); i++)
			cards.get(i).drop();
		cards.clear();
	}
	
	/*
	 * 展示初始的2张牌中的一张（一明一暗）
	 */
	public void showTwoCards() {
		System.out.println(cards.get(0).getName() + " *");
	}
	
	/*
	 * 展示这一手牌
	 */
	public void showCards() {
		for(int i = 0; i < cards.size(); i++)
			System.out.println(cards.get(i).getName());
	}
}
