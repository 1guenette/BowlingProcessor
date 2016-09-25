package hw1;

public class BowlingTest {
	public static void main(String[] args){
	TenPinBowling g = new TenPinBowling(3);
	g.roll(10);
	g.roll(10);
	g.roll(10);
	g.roll(10);
	System.out.println(g.getScore());

	
	}
}
