package card;

public class Test {
	public static void main(String[] args) {
		CardBank cb = new CardBank(1);
		for(int i = 1; i <= 20; i++) {
			Card c = cb.pick();
			System.out.println("第" + i + "次抽到了" + c.getName());
			System.out.println("牌库中有" + cb.getCardsRemain().size() + "张牌");
			System.out.println("场上有" + cb.getCardsOnStage().size() + "张牌");
			System.out.println("弃牌区有" + cb.getCardsDropped().size() + "张牌");
		}
		cb.finish();
		System.out.println("牌库中有" + cb.getCardsRemain().size() + "张牌");
		System.out.println("场上有" + cb.getCardsOnStage().size() + "张牌");
		System.out.println("弃牌区有" + cb.getCardsDropped().size() + "张牌");
	}
}
