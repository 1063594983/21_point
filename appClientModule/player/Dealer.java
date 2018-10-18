package player;

import java.util.ArrayList;

import card.Card;

public class Dealer {
	private int totalpoint;
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	/*
	 * ����ܵ���
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
	 * չʾ��ʼ��2�����е�һ�ţ�һ��һ����
	 */
	public void showTwoCards() {
		System.out.println(cards.get(0).getName() + " *");
	}
	
	/*
	 * չʾ��һ����
	 */
	public void showCards() {
		for(int i = 0; i < cards.size(); i++)
			System.out.println(cards.get(i).getName());
	}
}
