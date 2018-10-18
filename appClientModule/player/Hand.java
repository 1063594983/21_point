package player;

import java.util.ArrayList;

import card.Card;

public class Hand {
	private int bet;
	private int totalpoint;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	/*
	 * ���캯��
	 */
	public Hand(int bet) {
		this.bet = bet;
	}
	
	/*
	 * bet��set get����
	 */
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
		totalpoint = 0;
	}
	
	/*
	 * ��ȡ�ܵ���
	 */
	public int getTotolPoint() {
		return totalpoint;
	}
	
	/*
	 * �������е��ƣ����ƣ�
	 */
	public void addCard(Card card) {
		cards.add(card);
		totalpoint += card.getValue();
	}
	
	/*
	 * һ����Ϸ����ʱ�����е��Ʒ������Ƴ�
	 */
	public void dropCard() {
		for(int i = 0; i < cards.size(); i++)
			cards.get(i).drop();
		cards.clear();
	}
	
	/*
	 * չʾ��һ����
	 */
	public void showCards() {
		for(int i = 0; i < cards.size(); i++)
			System.out.println(cards.get(i).getName());
	}
	
}
