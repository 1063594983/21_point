package card;

public class Card {
	/*1-13 代表扑克牌的数值*/
	private int type;
	/*扑克牌的花色*/
	private String suit;
	/*扑克牌的状态 0-在牌库 1-在场上 2-在弃牌区*/
	private int status;
	
	/*构造函数*/
	public Card(int type, String suit) {
		this.type = type;
		this.suit = suit;
		this.status = 0;
	}
	
	/*获得扑克牌的名字*/
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
	
	/*获得扑克牌对应的值*/
	public int getValue() {
		if(type >= 10) {
			return 10;
		} else {
			return this.type;
		}
	}
	
	/*抽牌*/
	public void pick() {
		this.status = 1;
	}
	
	/*弃牌*/
	public void drop() {
		this.status = 2;
	}
	
	/*洗入牌库*/
	public void refresh() {
		this.status = 0;
	}
	
	/*得到牌的状态*/
	public int getStatus() {
		return this.status;
	}
}
