package player;

import java.util.ArrayList;

import card.Card;

public class Hand {
	private int bet;
	private int totalpoint;
	private int a_num; // A������
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	/*
	 * ���캯��
	 */
	public Hand(int bet) {
		this.bet = bet;
		totalpoint = 0;
		a_num = 0;
	}
	
	/*
	 * bet��set get����
	 */
	public int getBet() {
		return bet;
	}
	public void setBet(int bet) {
		this.bet = bet;
	}
	
	/*
	 * ��ȡ�ܵ���
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
	 * �������е��ƣ����ƣ�
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
	 * һ����Ϸ����ʱ�����е��Ʒ������Ƴ�
	 */
	public void dropCard() {
		cards.clear();
		totalpoint = 0;
		a_num = 0;
	}
	
	/*
	 * չʾ��һ����
	 */
	public void showCards() {
		for(int i = 0; i < cards.size(); i++)
			System.out.println(cards.get(i).getName());
	}
	
}
