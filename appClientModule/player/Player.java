package player;

import java.util.ArrayList;

public class Player {
	private String playername;
	private int money;
	private ArrayList<Hand> hands = new ArrayList<Hand>();
	
	public Player(String playername, int money) {
		this.playername = playername;
		this.money = money;
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
	}
	
	/*
	 * �ı����е�Ǯ��
	 */
	public void changeMoney(int bet) {
		money += bet;
	}
}
