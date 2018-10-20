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
	 * չʾ��ʼ��2�����е�һ�ţ�һ��һ����
	 */
	public void showTwoCards() {
		System.out.println("�������У�ׯ��չʾһ�����ƣ�" + cards.get(1).getName());
	}
	
	/*
	 * չʾ��һ����
	 */
	public void showCards() {
		System.out.print("ׯ��Ŀǰ���ƣ�");
		for(int i = 0; i < cards.size(); i++)
			System.out.print(cards.get(i).getName() + " ");
		System.out.print("\n");
	}
	
	//չʾ�ܵ���
	public void showTotalPoint() {
		System.out.println("ׯ���ܵ�����" + this.getTotalPoint());
		if (totalPoint > 21)
			System.out.println("�������ܵ�������21��ׯ�ұ���");
	}
	
	//�ж��Ƿ���BlackJack
	public void judgeBlackJack() {
		if (cards.size() == 2 && this.getTotalPoint() == 21) isBlackJack = true;
	}
	
	//��ʾׯ��Ӯ�˼���
	public void showWinCount() {
		System.out.println("ׯ��Ӯ��" + winCount + "��");
	}
	
}
