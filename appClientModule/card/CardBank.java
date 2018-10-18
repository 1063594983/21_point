package card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class CardBank {
	/*������*/
	private int numOfDeck;
	
	/*�ƿ��е���*/
	private ArrayList<Card> cardsRemain;
	
	/*���ϵ���*/
	private ArrayList<Card> cardsOnStage;
	
	/*����������*/
	private ArrayList<Card> cardsDropped;
	
	/*���캯��*/
	public CardBank(int num) {
		this.numOfDeck = num;
		cardsRemain = new ArrayList<>();
		cardsOnStage = new ArrayList<>();
		cardsDropped = new ArrayList<>();
		
		for(int i = 0; i < num; i++) {
			Deck d = new Deck();
			cardsRemain.addAll(d.getCards());
		}
	}
	
	/*���ƿ��г�һ����
	 * ���ƿ�Ϊ�գ����������е���ϴ���ƿ�
	 * */
	public Card pick() {
		int numOfCard = cardsRemain.size();
		if(numOfCard == 0) {
			refresh();
		}
		numOfCard = cardsRemain.size();
		Random rand = new Random();
		int index = rand.nextInt(numOfCard);
		Card c = cardsRemain.get(index);
		c.pick();
		cardsOnStage.add(c);
		cardsRemain.remove(index);
		return c;
	}
	
	/*ϴ�ƣ�������������ϴ���ƿ�*/
	public void refresh() {
		Iterator<Card> i = cardsDropped.iterator();
		while(i.hasNext()) {
			Card c = i.next();
			c.refresh();
		}
		
		cardsRemain.addAll(cardsDropped);
		cardsDropped.clear();
	}
	
	/*�����е��Ʒ���������*/
	public void finish() {
		Iterator<Card> i = cardsOnStage.iterator();
		while(i.hasNext()) {
			Card c = i.next();
			c.drop();
		}
		cardsDropped.addAll(cardsOnStage);
		cardsOnStage.clear();
	}
	
	/*����ƿ��е���*/
	public ArrayList<Card> getCardsRemain() {
		return cardsRemain;
	}
	
	/*��ó��ϵ���*/
	public ArrayList<Card> getCardsOnStage() {
		return cardsOnStage;
	}

	/*�������������*/
	public ArrayList<Card> getCardsDropped() {
		return cardsDropped;
	}
	
	
	
}
