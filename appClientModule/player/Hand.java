package player;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

import card.Card;

public class Hand {
	private int bet;
	private int totalPoint;
	private int a_num; // A的数量
	private ArrayList<Card> cards = new ArrayList<Card>();
	private boolean isBlackJack;
	
	/*
	 * 构造函数
	 */
	public Hand(int bet) {
		this.bet = bet;
		totalPoint = 0;
		a_num = 0;
		isBlackJack = false;
	}
	
	/*
	 * bet的set get函数
	 */
	public int getBet() {
		return bet;
	}
	
	public void setBet(int bet) {
		this.bet = bet;
		System.out.println("赌注已加至：" + bet);
	}
	
	public boolean isBlackJack() {
		return isBlackJack;
	}

	/*
	 * 获取总点数
	 */
	public int getTotalPoint() {
		totalPoint = 0;
		for (int i = 0; i < cards.size(); i++) totalPoint += cards.get(i).getValue();
		for (int i = 0; i < a_num; i++) {
			if (totalPoint + 10 <= 21) {
				totalPoint += 10;
			}
			else {
				break;
			}
		}
		return totalPoint;
	}
	
	/*
	 * 增加手中的牌（摸牌）
	 */
	public void addCard(Card card) {
		cards.add(card);
		totalPoint += card.getValue();
		if(card.getValue() == 1) {
			a_num++;
		}
	}
	
	/*
	 * 一局游戏结束时将手中的牌放入弃牌池
	 */
	public void dropCard() {
		cards.clear();
		totalPoint = 0;
		a_num = 0;
		isBlackJack = false;
	}
	
	/*
	 * 展示这一手牌
	 */
	public void showCards() {
		System.out.print("目前手牌：");
		for(int i = 0; i < cards.size(); i++)
			System.out.print(cards.get(i).getName() + " ");
		System.out.print("\n");
	}
	
	//显示总点数
	public void showTotalPoint() {
		System.out.println("总点数：" + this.getTotalPoint());
	}

	//判断是否是BlackJack
	public void judgeBlackJack() {
		if (cards.size() == 2 && this.getTotalPoint() == 21) isBlackJack = true;
	}
	
}
