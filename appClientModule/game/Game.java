package game;

import java.util.ArrayList;
import java.util.Scanner;
import card.*;
import player.*;


public class Game {
	
	private static Scanner sc;
	private static int instruction;		//输入指令，1表示开始新游戏，2表示查看比分，3表示退出游戏
	private static int playerCount;
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static Dealer d = new Dealer();
	
	public static void main(String[] args) {
		//第一次进入游戏，初始化玩家
		sc = new Scanner(System.in);
		System.out.println("输入玩家数量：");
		playerCount = sc.nextInt();
		for (int i = 0; i < playerCount; i++) {
			System.out.println("玩家" + (i+1) +"：");
			String name;
			int money;
			System.out.println("输入名字：");
			name = sc.next();
			System.out.println("输入拥有钱数：");
			money = sc.nextInt();
			Player player = new Player(name, money);
			players.add(player);
		}
		
		//游戏开始运行
		showInstructions();
		while (instruction != 3) {
			switch (instruction) {
			case 1:		//开始新游戏
				playGame();
				System.out.println("本局游戏结束！\n");
				break;
			case 2:		//查看比分
				d.showWinCount();
				for (int i = 0; i < players.size(); i++) players.get(i).showWinCount();
				break;
			}
//			//查看每个玩家的余额
//			for (int i = 0; i < players.size(); i++) {
//				System.out.println("玩家" + players.get(i).getPlayername() + "的余额：" + players.get(i).getMoney());
//			}
			showInstructions();
		}
		System.out.println("游戏退出！");
	}
	
	private static void playGame() {
		//初始化所有玩家的手牌
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int handCount;
			System.out.println(player.getPlayername() + "，您要玩几手牌？");
			handCount = sc.nextInt();
			int totalBet = 0;
			for (int j = 1; j <= handCount; j++) {
				if (totalBet == player.getMoney()) {
					System.out.println("余额已用完，无法继续添加！");
					break;
				}
				int bet;
				System.out.println("输入第" + j + "手的赌注：");
				bet = sc.nextInt();
				totalBet += bet;
				while (totalBet > player.getMoney()) {
					System.out.println("余额不足，无法添加!请再次输入：");
					totalBet -= bet;
					bet = sc.nextInt();
					totalBet += bet;
				}
				Hand hand = new Hand(bet);
				player.addHand(hand);
			}
		}
		
		//发牌
		System.out.println("开始发牌！");
		CardBank cb = new CardBank(1);		//一副扑克牌
		//给玩家发牌
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			for (int j = 0; j < player.getHands().size(); j++) {
				Hand hand = player.getHands().get(j);
				Card card1, card2;
				card1 = cb.pick();
				card2 = cb.pick();
				hand.addCard(card1);
				hand.addCard(card2);
				System.out.println(player.getPlayername() + "的第" + (j+1) + "手牌发到了：" + card1.getName() + "和" + card2.getName());
			}
		}
		//给庄家发牌
		Card card1, card2;
		card1 = cb.pick();
		card2 = cb.pick();
		d.addCard(card1);
		d.addCard(card2);
		d.showTwoCards();	//庄家显示一张明牌
		
		//判断玩家的每一手牌和庄家是否是BlackJack
		//玩家
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			for (int j = 0; j < player.getHands().size(); j++) {
				Hand h = player.getHands().get(j);
				h.judgeBlackJack();
				if (h.isBlackJack()) 
					System.out.println(player.getPlayername() + "的第" + (j+1) + "手牌是BlackJack！");
			}
		}
		//庄家
		d.judgeBlackJack();
		
		//每个玩家开始游戏
		System.out.println("游戏开始！");
		System.out.println("每一非BlackJack手牌，输入:\n1：加倍\t2：拿牌\t3：停牌");
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
				for (int j = 0; j < player.getHands().size(); j++) {
					System.out.println(player.getPlayername() + "的第" + (j+1) + "手牌开始！");
					Hand hand = player.getHands().get(j);
					hand.showCards();
					hand.showTotalPoint();
					if (!hand.isBlackJack()) {
						int choose;			//非BlackJack玩家的选择
						choose = sc.nextInt();
						boolean getCard = false;	//是否拿过牌
						boolean lose = false;		//是否爆牌
						boolean stop = false;		//是否停牌
						while (choose != 3 && !lose && !stop) {
							if (choose == 2) getCard = true;
							if (choose == 1 && getCard == true) {
								System.out.println("拿牌之后，只能拿牌或停牌！");
							}
							switch (choose) {
							case 1:		//加倍
								int bet = hand.getBet();
								if (bet + player.getAllBets() > player.getMoney()) {
									System.out.println("余额不足，无法加倍！");
								}
								else {
									hand.setBet(bet * 2);
								}
								break;
							case 2:		//拿牌
								Card card = cb.pick();
								System.out.println("拿到牌：" + card.getName());
								hand.addCard(card);
								hand.showCards();
								hand.showTotalPoint();
								if (hand.getTotalPoint() > 21) {
									System.out.println("所有牌总点数大于21，爆牌！");
									lose = true;
								}
								break;
							case 3:		//停牌
								stop = true;
								break;
							}
							if (!lose) choose = sc.nextInt();
						}
					}
					else {
						System.out.println("那是真的NB，这手牌是BlackJack！");
					}
				}
			}
		
		//庄家开始游戏
		System.out.println("庄家翻开暗牌：" + card1.getName());
		d.showCards();
		while (d.getTotalPoint() < 17) {
			Card card = cb.pick();
			System.out.println("庄家拿牌：" + card.getName());
			d.addCard(card);
		}
		System.out.println("庄家结束拿牌");
		d.showCards();
		d.showTotalPoint();
		
		//庄家与每位玩家的每一手牌比较得分，计算赌注的得失
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			for (int j = 0; j < player.getHands().size(); j++) {
				System.out.println(player.getPlayername() + "的第" + (j+1) + "手牌开始结算：");
				Hand hand = player.getHands().get(j);
				hand.showTotalPoint();
				String name = player.getPlayername();
				int bet = hand.getBet();
				if (hand.isBlackJack()) {		//玩家这手牌是BlackJack
					System.out.println("玩家" + name + "这手牌是BlackJack!");
					if (d.isBlackJack()) {		//庄家是BlackJack
						System.out.println("庄家也是BlackJack!");
						System.out.println("平局，玩家" + name + "拿回这手牌的赌注");
					}
					else {		//庄家不是BlackJack
						System.out.println("庄家不是BlackJack!");
						System.out.println("BlackJack大于任何点数，玩家" + name + "赢得2倍赌注（$" + 2 * bet + "）！");
						player.win(2 * bet);
					}
				}
				else {		//玩家这手牌不是BlackJack
					if (d.isBlackJack()) {		//庄家是BlackJack
						System.out.println("玩家" + name + "这手牌不是BlackJack，但庄家是BlackJack!");
						System.out.println("BlackJack大于任何点数，庄家赢得1倍赌注（$" + bet + "）！");
						d.win();
						player.lose(bet);
					}
					else {		//玩家和庄家都不是BlackJack
						if (hand.getTotalPoint() > 21) {		//玩家爆牌(若玩家和庄家都爆牌，优先考虑玩家，即庄家赢)
							System.out.println("玩家" + name + "爆牌，庄家赢得1倍赌注（$" + bet + "）！");
							d.win();
							player.lose(bet);
						}
						else  {
							if (d.getTotalPoint() > 21) {		//庄家爆牌
								System.out.println("庄家爆牌，玩家" + name + "赢得1倍赌注（$" + bet + "）！");
								player.win(bet);
							}
							else {		//玩家和庄家都不是BlackJack，并且都没有爆牌，则比较点数大小，点数大的获胜
								if (d.getTotalPoint() > hand.getTotalPoint()) {
									System.out.println("庄家赢得1倍赌注（$" + bet + "）！");
									d.win();
									player.lose(bet);
								}
								else if (d.getTotalPoint() < hand.getTotalPoint()) {
									System.out.println("玩家" + name + "赢得1倍赌注（$" + bet + "）！");
									player.win(bet);
								}
								else {
									System.out.println("平局，玩家" + name + "拿回这手牌的赌注");
								}
							}
						}
					}
				}
			}
		}
		
		//玩家和庄家弃牌
		for (int i = 0; i < players.size(); i++) players.get(i).clearHand();
		d.dropCard();
	}

	//新游戏开始时的提示信息
	public static void showInstructions() {
		instruction = 0;
		System.out.println("21 Point Game!");
		System.out.println("输入:\n1：开始新游戏\t2：查看比分\t3：退出游戏");
		System.out.println("请输入：");
		instruction = sc.nextInt();
		while (!(instruction == 1 || instruction == 2 || instruction == 3)) {
			System.out.println("指令错误，请重新输入！");
			instruction = sc.nextInt();
		}
	}
	
}
