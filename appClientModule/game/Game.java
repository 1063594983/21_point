package game;

import java.util.ArrayList;
import java.util.Scanner;
import card.*;
import player.*;


public class Game {
	
	private static Scanner sc;
	private static int instruction;		//����ָ�1��ʾ��ʼ����Ϸ��2��ʾ�鿴�ȷ֣�3��ʾ�˳���Ϸ
	private static int playerCount;
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static Dealer d = new Dealer();
	
	public static void main(String[] args) {
		//��һ�ν�����Ϸ����ʼ�����
		sc = new Scanner(System.in);
		System.out.println("�������������");
		playerCount = sc.nextInt();
		for (int i = 0; i < playerCount; i++) {
			System.out.println("���" + (i+1) +"��");
			String name;
			int money;
			System.out.println("�������֣�");
			name = sc.next();
			System.out.println("����ӵ��Ǯ����");
			money = sc.nextInt();
			Player player = new Player(name, money);
			players.add(player);
		}
		
		//��Ϸ��ʼ����
		showInstructions();
		while (instruction != 3) {
			switch (instruction) {
			case 1:		//��ʼ����Ϸ
				playGame();
				System.out.println("������Ϸ������\n");
				break;
			case 2:		//�鿴�ȷ�
				d.showWinCount();
				for (int i = 0; i < players.size(); i++) players.get(i).showWinCount();
				break;
			}
//			//�鿴ÿ����ҵ����
//			for (int i = 0; i < players.size(); i++) {
//				System.out.println("���" + players.get(i).getPlayername() + "����" + players.get(i).getMoney());
//			}
			showInstructions();
		}
		System.out.println("��Ϸ�˳���");
	}
	
	private static void playGame() {
		//��ʼ��������ҵ�����
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int handCount;
			System.out.println(player.getPlayername() + "����Ҫ�漸���ƣ�");
			handCount = sc.nextInt();
			int totalBet = 0;
			for (int j = 1; j <= handCount; j++) {
				if (totalBet == player.getMoney()) {
					System.out.println("��������꣬�޷�������ӣ�");
					break;
				}
				int bet;
				System.out.println("�����" + j + "�ֵĶ�ע��");
				bet = sc.nextInt();
				totalBet += bet;
				while (totalBet > player.getMoney()) {
					System.out.println("���㣬�޷����!���ٴ����룺");
					totalBet -= bet;
					bet = sc.nextInt();
					totalBet += bet;
				}
				Hand hand = new Hand(bet);
				player.addHand(hand);
			}
		}
		
		//����
		System.out.println("��ʼ���ƣ�");
		CardBank cb = new CardBank(1);		//һ���˿���
		//����ҷ���
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			for (int j = 0; j < player.getHands().size(); j++) {
				Hand hand = player.getHands().get(j);
				Card card1, card2;
				card1 = cb.pick();
				card2 = cb.pick();
				hand.addCard(card1);
				hand.addCard(card2);
				System.out.println(player.getPlayername() + "�ĵ�" + (j+1) + "���Ʒ����ˣ�" + card1.getName() + "��" + card2.getName());
			}
		}
		//��ׯ�ҷ���
		Card card1, card2;
		card1 = cb.pick();
		card2 = cb.pick();
		d.addCard(card1);
		d.addCard(card2);
		d.showTwoCards();	//ׯ����ʾһ������
		
		//�ж���ҵ�ÿһ���ƺ�ׯ���Ƿ���BlackJack
		//���
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			for (int j = 0; j < player.getHands().size(); j++) {
				Hand h = player.getHands().get(j);
				h.judgeBlackJack();
				if (h.isBlackJack()) 
					System.out.println(player.getPlayername() + "�ĵ�" + (j+1) + "������BlackJack��");
			}
		}
		//ׯ��
		d.judgeBlackJack();
		
		//ÿ����ҿ�ʼ��Ϸ
		System.out.println("��Ϸ��ʼ��");
		System.out.println("ÿһ��BlackJack���ƣ�����:\n1���ӱ�\t2������\t3��ͣ��");
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
				for (int j = 0; j < player.getHands().size(); j++) {
					System.out.println(player.getPlayername() + "�ĵ�" + (j+1) + "���ƿ�ʼ��");
					Hand hand = player.getHands().get(j);
					hand.showCards();
					hand.showTotalPoint();
					if (!hand.isBlackJack()) {
						int choose;			//��BlackJack��ҵ�ѡ��
						choose = sc.nextInt();
						boolean getCard = false;	//�Ƿ��ù���
						boolean lose = false;		//�Ƿ���
						boolean stop = false;		//�Ƿ�ͣ��
						while (choose != 3 && !lose && !stop) {
							if (choose == 2) getCard = true;
							if (choose == 1 && getCard == true) {
								System.out.println("����֮��ֻ�����ƻ�ͣ�ƣ�");
							}
							switch (choose) {
							case 1:		//�ӱ�
								int bet = hand.getBet();
								if (bet + player.getAllBets() > player.getMoney()) {
									System.out.println("���㣬�޷��ӱ���");
								}
								else {
									hand.setBet(bet * 2);
								}
								break;
							case 2:		//����
								Card card = cb.pick();
								System.out.println("�õ��ƣ�" + card.getName());
								hand.addCard(card);
								hand.showCards();
								hand.showTotalPoint();
								if (hand.getTotalPoint() > 21) {
									System.out.println("�������ܵ�������21�����ƣ�");
									lose = true;
								}
								break;
							case 3:		//ͣ��
								stop = true;
								break;
							}
							if (!lose) choose = sc.nextInt();
						}
					}
					else {
						System.out.println("�������NB����������BlackJack��");
					}
				}
			}
		
		//ׯ�ҿ�ʼ��Ϸ
		System.out.println("ׯ�ҷ������ƣ�" + card1.getName());
		d.showCards();
		while (d.getTotalPoint() < 17) {
			Card card = cb.pick();
			System.out.println("ׯ�����ƣ�" + card.getName());
			d.addCard(card);
		}
		System.out.println("ׯ�ҽ�������");
		d.showCards();
		d.showTotalPoint();
		
		//ׯ����ÿλ��ҵ�ÿһ���ƱȽϵ÷֣������ע�ĵ�ʧ
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			for (int j = 0; j < player.getHands().size(); j++) {
				System.out.println(player.getPlayername() + "�ĵ�" + (j+1) + "���ƿ�ʼ���㣺");
				Hand hand = player.getHands().get(j);
				hand.showTotalPoint();
				String name = player.getPlayername();
				int bet = hand.getBet();
				if (hand.isBlackJack()) {		//�����������BlackJack
					System.out.println("���" + name + "��������BlackJack!");
					if (d.isBlackJack()) {		//ׯ����BlackJack
						System.out.println("ׯ��Ҳ��BlackJack!");
						System.out.println("ƽ�֣����" + name + "�û������ƵĶ�ע");
					}
					else {		//ׯ�Ҳ���BlackJack
						System.out.println("ׯ�Ҳ���BlackJack!");
						System.out.println("BlackJack�����κε��������" + name + "Ӯ��2����ע��$" + 2 * bet + "����");
						player.win(2 * bet);
					}
				}
				else {		//��������Ʋ���BlackJack
					if (d.isBlackJack()) {		//ׯ����BlackJack
						System.out.println("���" + name + "�����Ʋ���BlackJack����ׯ����BlackJack!");
						System.out.println("BlackJack�����κε�����ׯ��Ӯ��1����ע��$" + bet + "����");
						d.win();
						player.lose(bet);
					}
					else {		//��Һ�ׯ�Ҷ�����BlackJack
						if (hand.getTotalPoint() > 21) {		//��ұ���(����Һ�ׯ�Ҷ����ƣ����ȿ�����ң���ׯ��Ӯ)
							System.out.println("���" + name + "���ƣ�ׯ��Ӯ��1����ע��$" + bet + "����");
							d.win();
							player.lose(bet);
						}
						else  {
							if (d.getTotalPoint() > 21) {		//ׯ�ұ���
								System.out.println("ׯ�ұ��ƣ����" + name + "Ӯ��1����ע��$" + bet + "����");
								player.win(bet);
							}
							else {		//��Һ�ׯ�Ҷ�����BlackJack�����Ҷ�û�б��ƣ���Ƚϵ�����С��������Ļ�ʤ
								if (d.getTotalPoint() > hand.getTotalPoint()) {
									System.out.println("ׯ��Ӯ��1����ע��$" + bet + "����");
									d.win();
									player.lose(bet);
								}
								else if (d.getTotalPoint() < hand.getTotalPoint()) {
									System.out.println("���" + name + "Ӯ��1����ע��$" + bet + "����");
									player.win(bet);
								}
								else {
									System.out.println("ƽ�֣����" + name + "�û������ƵĶ�ע");
								}
							}
						}
					}
				}
			}
		}
		
		//��Һ�ׯ������
		for (int i = 0; i < players.size(); i++) players.get(i).clearHand();
		d.dropCard();
	}

	//����Ϸ��ʼʱ����ʾ��Ϣ
	public static void showInstructions() {
		instruction = 0;
		System.out.println("21 Point Game!");
		System.out.println("����:\n1����ʼ����Ϸ\t2���鿴�ȷ�\t3���˳���Ϸ");
		System.out.println("�����룺");
		instruction = sc.nextInt();
		while (!(instruction == 1 || instruction == 2 || instruction == 3)) {
			System.out.println("ָ��������������룡");
			instruction = sc.nextInt();
		}
	}
	
}
