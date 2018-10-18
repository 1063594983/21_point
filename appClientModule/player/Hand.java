package player;

import java.util.ArrayList;

import card.Card;

public class Hand {
	private int bet;
	private int totalpoint;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	/*
	 * 构造函数
	 */
	public Hand(int bet) {
		this.bet = bet;
	}
	
	/*
	 * bet的set get函数
	 */
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
		totalpoint = 0;
	}
	
	/*
	 * 获取总点数
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
	 * 展示这一手牌
	 */
	public void showCards() {
		for(int i = 0; i < cards.size(); i++)
			System.out.println(cards.get(i).getName());
	}
	
}
