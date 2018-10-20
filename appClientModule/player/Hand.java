package player;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.BlockAction;

import card.Card;

public class Hand {
	private int bet;
	private int totalPoint;
	private int a_num; // A������
	private ArrayList<Card> cards = new ArrayList<Card>();
	private boolean isBlackJack;
	
	/*
	 * ���캯��
	 */
	public Hand(int bet) {
		this.bet = bet;
		totalPoint = 0;
		a_num = 0;
		isBlackJack = false;
	}
	
	/*
	 * bet��set get����
	 */
	public int getBet() {
		return bet;
	}
	
	public void setBet(int bet) {
		this.bet = bet;
		System.out.println("��ע�Ѽ�����" + bet);
	}
	
	public boolean isBlackJack() {
		return isBlackJack;
	}

	/*
	 * ��ȡ�ܵ���
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
	 * �������е��ƣ����ƣ�
	 */
	public void addCard(Card card) {
		cards.add(card);
		totalPoint += card.getValue();
		if(card.getValue() == 1) {
			a_num++;
		}
	}
	
	/*
	 * һ����Ϸ����ʱ�����е��Ʒ������Ƴ�
	 */
	public void dropCard() {
		cards.clear();
		totalPoint = 0;
		a_num = 0;
		isBlackJack = false;
	}
	
	/*
	 * չʾ��һ����
	 */
	public void showCards() {
		System.out.print("Ŀǰ���ƣ�");
		for(int i = 0; i < cards.size(); i++)
			System.out.print(cards.get(i).getName() + " ");
		System.out.print("\n");
	}
	
	//��ʾ�ܵ���
	public void showTotalPoint() {
		System.out.println("�ܵ�����" + this.getTotalPoint());
	}

	//�ж��Ƿ���BlackJack
	public void judgeBlackJack() {
		if (cards.size() == 2 && this.getTotalPoint() == 21) isBlackJack = true;
	}
	
}
