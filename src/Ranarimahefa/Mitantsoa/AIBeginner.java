package Ranarimahefa.Mitantsoa;

import java.util.ArrayList;

public class AIBeginner extends AI {
	
	public AIBeginner(){
		this.listShip = new ArrayList<Ship>();
		this.shootHit = new ArrayList<String>();
		this.missedShoot =  new ArrayList<String>();
	}
	
	public String getName(){
		return "AI Beginner";
	}
	
	public String shoot(){
		return this.generateCoord();
	}

}
