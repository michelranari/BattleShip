import java.util.concurrent.ThreadLocalRandom;


public class AI extends Player {

	public AI(){
		super("AI");
	}
	
	public String randomShoot(){
		int randomNum1 = ThreadLocalRandom.current().nextInt(1, 11);
		char xCoord = (char)('a' + randomNum1);
		int yCoord = ThreadLocalRandom.current().nextInt(1, 11);
		String coord = "" + xCoord + yCoord;
		return coord;
	}
	
	public boolean isInMissedShoot(String shoot){
		boolean res = false;
		int i = 0;
		while (res == false && i < super.getMissedShoot().size()){
			if ()
		}
	}
	public static void main(String[] args) {
		AI intel = new AI();
		System.out.println(intel.randomShoot());
	}
}
