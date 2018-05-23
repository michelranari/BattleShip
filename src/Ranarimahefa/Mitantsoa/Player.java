package Ranarimahefa.Mitantsoa;

import java.util.ArrayList;

public abstract class Player {
	
	ArrayList<Ship> listShip;
	ArrayList<String> shootHit;		// shots that hit the enemy
	ArrayList<String> missedShoot;	// failed shoot
	
	abstract String getName();
	abstract void createArmy();
	abstract boolean checkBoxShipOccupied(char XstartCoord, int YstartCoord, boolean orientation, int sizeShipEntry );
	abstract String shoot();
	abstract void displayShoot();
	abstract boolean restart();
	
	/* ------------------------------------ Getter & Setter ----------------------------------*/
	
	public ArrayList<String> getMissedShoot() {
		return missedShoot;
	}

	public void setMissedShoot(ArrayList<String> missedShoot) {
		this.missedShoot = missedShoot;
	}

	public ArrayList<Ship> getListShip() {
		return listShip;
	}
	
	public void setListShip(ArrayList<Ship> listShip) {
		this.listShip = listShip;
	}
	
	public ArrayList<String> getShootHit() {
		return shootHit;
	}
	
	public void setShootHit(ArrayList<String> shootHit) {
		this.shootHit = shootHit;
	}
	
	public Ship getShip(int pos){
		return this.getListShip().get(pos);
	}
	
	public String getAShootHit(int pos){
		return this.getShootHit().get(pos);
	}
	
	public String getAMissedShoot(int pos){
		return this.getMissedShoot().get(pos);
	}
	
	
	// return the ship hit by the missile of x and y coordinates
	protected Ship getHitShip(char xCoord, int yCoord){
		int indexShip = 0;
		boolean res = false;
		int i = 0;
		while (res == false && i < this.getListShip().size()){
			Ship s = this.getShip(i);
			if (s.isHit(xCoord, yCoord)){
				res = true;
				indexShip = i;
			}
			i++;
		}
		return this.getShip(indexShip);
	}
	
	// return the index of the ship hit by the missile of x and y coordinates
		protected int getIndexHitShip(char xCoord, int yCoord){
			int indexShip = 0;
			boolean res = false;
			int i = 0;
			while (res == false && i < this.getListShip().size()){
				Ship s = this.getShip(i);
				if (s.isHit(xCoord, yCoord)){
					res = true;
					indexShip = i;
				}
				i++;
			}
			return indexShip;
		}
	
	
	// return true if a ship of this player is hited by the missile of x and y coordinates, false otherwise
	protected boolean isHitPlayer(char xCoord, int yCoord){
		boolean res = false;
		int i = 0;
		while (res == false && i < this.getListShip().size()){
			Ship s = this.getShip(i);
			res = s.isHit(xCoord, yCoord);
			i++;
		}
		return res;
	}
	
	protected void setHit(char xCoord, int yCoord){
		Ship shipHit = this.getHitShip(xCoord, yCoord);
		Box boxHit = shipHit.getHitBox(xCoord, yCoord);
		boxHit.setTouched(true);
	}
	
	// return true if the player lost the game, i.e. if all these boats were sunk. False otherwise
	protected boolean hasLost(){
		return this.getListShip().isEmpty();
	}
	
	public void resetPlayer(){
		this.getListShip().clear();
		this.getMissedShoot().clear();
		this.getShootHit().clear();
	}
	
}
