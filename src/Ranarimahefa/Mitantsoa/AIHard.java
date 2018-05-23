package Ranarimahefa.Mitantsoa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class AIHard extends AI{
	
	ArrayList<String> crossShoot;
	
	public AIHard(){
		this.listShip = new ArrayList<Ship>();
		this.shootHit = new ArrayList<String>();
		this.missedShoot =  new ArrayList<String>();
		this.crossShoot = new ArrayList<String>();
		this.generateTarget();
	}
	
	public String getName(){
		return "AI Hard";
	}

	public ArrayList<String> getCrossShoot() {
		return crossShoot;
	}

	public void setCrossShoot(ArrayList<String> crossShoot) {
		this.crossShoot = crossShoot;
	}

	public String shoot(){
		String coord;
		if (!(this.getCrossShoot().isEmpty())){
			coord = this.randomCrossShoot();
		}
		else{
			do{
				coord = this.generateCoord();
			}while(existInMissed(coord) || existInSuccessfulShoot(coord));
		}
		return coord;
	}
	
	public String randomCrossShoot(){
		String coord;
		Random random = new Random();
		int randomS = random.nextInt(this.getCrossShoot().size());
		coord = this.getCrossShoot().get(randomS);
		this.getCrossShoot().remove(randomS);
		return coord;
	}
	
	public void resetPlayer(){
		this.getListShip().clear();
		this.getMissedShoot().clear();
		this.getShootHit().clear();
		this.getCrossShoot().clear();
	}
	
	public void generateTarget(){
		for (int row = 1; row < Rules.MAP_SIZE + 1; row++)
	    {
	        for (int column = 1 ; column < Rules.MAP_SIZE + 1; column++)
	        {
	        	if ( (row%2 != 0 && column%2 != 0) || (row%2 == 0 && column%2 == 0) ){
	        		
	        		this.getCrossShoot().add(""+(char)('a'+column-1)+ row);
	        	}
	        }
	    }
	}
	
	public static void main(String[] args) {
		AIHard a = new AIHard();
		AIMedium b = new AIMedium();
		
		GameEngine game = new GameEngine(a,b);
		game.gameLoop();
		game.results();
		
	}
}
