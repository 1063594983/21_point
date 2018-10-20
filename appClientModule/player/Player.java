package player;

import java.util.ArrayList;

public class Player {
	private String playername;
	private int money;
	private ArrayList<Hand> hands = new ArrayList<Hand>();
	private int winCount;
	
	public Player(String playername, int money) {
		this.playername = playername;
		this.money = money;
		winCount = 0;
	}
	
	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String name) {
		this.playername = name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public ArrayList<Hand> getHands() {
		return hands;
	}
	
	public int getWinCount() {
		return winCount;
	}

	//һ����Ӯ�ˣ����winCount++������Ӯ�ö�ע
	public void win(int bet) {
		winCount++;
		changeMoney(bet);
	}
	
	//һ�������ˣ����ʧȥ��ע
	public void lose(int bet) {
		changeMoney(-bet);
	}
	
	//������������ж�ע���ܺ�
	public int getAllBets() {
		int totalBet = 0;
		for (int i = 0; i < hands.size(); i++) totalBet += hands.get(i).getBet();
		return totalBet;
	}
	
	/*
	 * ����һ����
	 */
	public void addHand(Hand hand) {
		hands.add(hand);
	}
	
	/*
	 * һ����Ϸ�����ӵ�������
	 */
	public void clearHand() {
		for(int i = 0; i < hands.size(); i++) 
			hands.get(i).dropCard();
		hands.clear();
	}
	
	/*
	 * �ı����е�Ǯ��
	 */
	public void changeMoney(int bet) {
		money += bet;
	}
	
	//��ʾ�����Ӯ�ľ���
	public void showWinCount() {
		System.out.println("���" + playername + "Ӯ��" + winCount + "��");
	}
	
}
