package card;

public class Test {
	public static void main(String[] args) {
		CardBank cb = new CardBank(1);
		for(int i = 1; i <= 20; i++) {
			Card c = cb.pick();
			System.out.println("��" + i + "�γ鵽��" + c.getName());
			System.out.println("�ƿ�����" + cb.getCardsRemain().size() + "����");
			System.out.println("������" + cb.getCardsOnStage().size() + "����");
			System.out.println("��������" + cb.getCardsDropped().size() + "����");
		}
		cb.finish();
		System.out.println("�ƿ�����" + cb.getCardsRemain().size() + "����");
		System.out.println("������" + cb.getCardsOnStage().size() + "����");
		System.out.println("��������" + cb.getCardsDropped().size() + "����");
	}
}
