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

	//一手牌赢了，玩家winCount++，并且赢得赌注
	public void win(int bet) {
		winCount++;
		changeMoney(bet);
	}
	
	//一手牌输了，玩家失去赌注
	public void lose(int bet) {
		changeMoney(-bet);
	}
	
	//玩家所有手牌中赌注的总和
	public int getAllBets() {
		int totalBet = 0;
		for (int i = 0; i < hands.size(); i++) totalBet += hands.get(i).getBet();
		return totalBet;
	}
	
	/*
	 * 增加一手牌
	 */
	public void addHand(Hand hand) {
		hands.add(hand);
	}
	
	/*
	 * 一局游戏结束扔掉所有牌
	 */
	public void clearHand() {
		for(int i = 0; i < hands.size(); i++) 
			hands.get(i).dropCard();
		hands.clear();
	}
	
	/*
	 * 改变手中的钱数
	 */
	public void changeMoney(int bet) {
		money += bet;
	}
	
	//显示该玩家赢的局数
	public void showWinCount() {
		System.out.println("玩家" + playername + "赢了" + winCount + "场");
	}
	
}
