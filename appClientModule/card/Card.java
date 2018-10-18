package card;

public class Card {
	/*1-13 �����˿��Ƶ���ֵ*/
	private int type;
	/*�˿��ƵĻ�ɫ*/
	private String suit;
	/*�˿��Ƶ�״̬ 0-���ƿ� 1-�ڳ��� 2-��������*/
	private int status;
	
	/*���캯��*/
	public Card(int type, String suit) {
		this.type = type;
		this.suit = suit;
		this.status = 0;
	}
	
	/*����˿��Ƶ�����*/
	public String getName() {
		switch(type) {
		case 1:
			return suit + "A";
		case 11 :
			return suit + "J";
		case 12:
			return suit + "Q";
		case 13:
			return suit + "K";
		default:
			return suit + this.type;
		}	
	}
	
	/*����˿��ƶ�Ӧ��ֵ*/
	public int getValue() {
		if(type >= 10) {
			return 10;
		} else {
			return this.type;
		}
	}
	
	/*����*/
	public void pick() {
		this.status = 1;
	}
	
	/*����*/
	public void drop() {
		this.status = 2;
	}
	
	/*ϴ���ƿ�*/
	public void refresh() {
		this.status = 0;
	}
	
	/*�õ��Ƶ�״̬*/
	public int getStatus() {
		return this.status;
	}
}
