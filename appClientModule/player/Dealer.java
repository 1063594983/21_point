package player;

import java.util.ArrayList;

import card.Card;

public class Dealer {
	private int totalpoint;
	private int a_num;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	public Dealer() {
		totalpoint = 0;
		a_num = 0;
	}

	/*
	 * 获取总点数
	 */
	public int getTotalPoint() {
		for(int i = 0; i < a_num; i++) {
			if(totalpoint <= 21) break;
			else {
				totalpoint -= 10;
			}
		}
		return totalpoint;
	}
	
	/*
	 * 增加手中的牌（摸牌）
	 */
	public void addCard(Card card) {
		cards.add(card);
		if(card.getValue() != 1)
			totalpoint += card.getValue();
		else {
			a_num++;
			totalpoint += 11;
		}
	}
	
	/*
	 * 一局游戏结束时将手中的牌放入弃牌池
	 */
	public void dropCard() {
		cards.clear();
		totalpoint = 0;
		a_num = 0;
	}
	
	/*
	 * 展示初始的2张牌中的一张（一明一暗）
	 */
	public void showTwoCards() {
		System.out.println("* " + cards.get(1).getName());
	}
	
	/*
	 * 展示这一手牌
	 */
	public void showCards() {
		for(int i = 0; i < cards.size(); i++)
			System.out.println(cards.get(i).getName());
	}
}
