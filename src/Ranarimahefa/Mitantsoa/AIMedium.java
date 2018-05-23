package Ranarimahefa.Mitantsoa;

import java.util.ArrayList;

public class AIMedium extends AI{
	
	public AIMedium(){
		this.listShip = new ArrayList<Ship>();
		this.shootHit = new ArrayList<String>();
		this.missedShoot =  new ArrayList<String>();
	}
	
	public String getName(){
		return "AI Medium";
	}
	
	public String shoot(){
		String coord;
		do{
			System.out.println("taille :" + (this.getMissedShoot().size() + this.getShootHit().size()));
			coord = this.generateCoord();
			
		}while(existInMissed(coord) || existInSuccessfulShoot(coord));
		return coord;
	}
	
	public static void main(String[] args) {
		
		AIMedium a= new AIMedium();
		a.createArmy();
		
		for (int i = 0;i<100;i++){
			String shoot = a.shoot();
			a.getMissedShoot().add(shoot);
			System.out.println(a.getMissedShoot());
			System.out.println("taille :" + a.getMissedShoot().size());
			System.out.println(shoot);
		}
		
	}

}
