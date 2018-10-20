package player;

import java.util.ArrayList;

import card.Card;

public class Dealer {
	private int totalPoint;
	private int a_num;
	private ArrayList<Card> cards = new ArrayList<Card>();
	private int winCount;
	private boolean isBlackJack;
	
	public Dealer() {
		totalPoint = 0;
		a_num = 0;
		winCount = 0;
		isBlackJack = false;
	}

	public int getWinCount() {
		return winCount;
	}

	public void win() {
		winCount++;
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
	 * 展示初始的2张牌中的一张（一明一暗）
	 */
	public void showTwoCards() {
		System.out.println("两张牌中，庄家展示一张明牌：" + cards.get(1).getName());
	}
	
	/*
	 * 展示这一手牌
	 */
	public void showCards() {
		System.out.print("庄家目前手牌：");
		for(int i = 0; i < cards.size(); i++)
			System.out.print(cards.get(i).getName() + " ");
		System.out.print("\n");
	}
	
	//展示总点数
	public void showTotalPoint() {
		System.out.println("庄家总点数：" + this.getTotalPoint());
		if (totalPoint > 21)
			System.out.println("所有牌总点数大于21，庄家爆牌");
	}
	
	//判断是否是BlackJack
	public void judgeBlackJack() {
		if (cards.size() == 2 && this.getTotalPoint() == 21) isBlackJack = true;
	}
	
	//显示庄家赢了几场
	public void showWinCount() {
		System.out.println("庄家赢了" + winCount + "场");
	}
	
}
