package Ranarimahefa.Mitantsoa;
import java.util.*;

public class Ship {
	
	ArrayList<Box> coordinates;
	String direction; // vertical or horizontal
	int sizeShip; // not sure
	
	public Ship(String startCoord, String endCoord){
		
		this.coordinates = new ArrayList<>();
		
		char XstartCoord = Character.toLowerCase(startCoord.charAt(0));
		int YstartCoord =  Integer.parseInt(startCoord.substring(1));
		char XendCoord = Character.toLowerCase(endCoord.charAt(0));
		int YendCoord = Integer.parseInt(endCoord.substring(1));
		
		if(XstartCoord == XendCoord){
			this.direction = "vertical";
			this.sizeShip = (YendCoord -  YstartCoord) + 1;
			
			for (int i = 0 ; i < this.sizeShip ; i++ ){
				Box c = new Box(XstartCoord,YstartCoord + i);
				coordinates.add(c);
			}
		}
		else{
			this.direction = "horizontal";
			this.sizeShip = (XendCoord -  XstartCoord) + 1;
			
			for (int i = 0 ; i < this.sizeShip ; i++ ){
				char car = (char)(XstartCoord + i);
				Box b = new Box(car,YstartCoord);
				coordinates.add(b);
			}
		}
		
	}
	

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getSizeShip() {
		return sizeShip;
	}

	public void setSizeShip(int sizeShip) {
		this.sizeShip = sizeShip;
	}

	public ArrayList<Box> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Box> coordinates) {
		this.coordinates = coordinates;
	}
	
	public Box getBox(int pos){
		return this.getCoordinates().get(pos);
	}
	
	// return true if the ship is hit by the missile
	public boolean isHit(char xCoord, int yCoord){
		return !(this.boxIsInShip(xCoord,yCoord));
	}
	
	// return true if the ship has sunk
	public boolean isDestroyed(){
		boolean res = true;
		int i = 0;
		while (res == true && i < this.getCoordinates().size()){
			Box b = this.getBox(i);
			if (b.touched == false){
				res = false;
			}
			i++;
		}
		return res;
	}
	
	// return the box hit by the missile of x and y coordinates
	public Box getHitBox(char xCoord, int yCoord){
		int indexBox = 0;
		int i = 0;
		boolean test = true;
		while ( test == true && i < this.getCoordinates().size()){
			Box b = this.getBox(i);
			char x = b.getX();
			int y = b.getY();
			if ( x == xCoord && y == yCoord ){
				indexBox = i;
				test = false;
			}
			i++;
		}
		return this.getBox(indexBox);
		
		
	}
	
	// return false if the box of x and y coordinates is a box of the ship, true otherwise
	public boolean boxIsInShip(char xCoord, int yCoord ){
		boolean res = true;
		int i = 0;
		while (res == true && i < this.getCoordinates().size()){
			Box b = this.getBox(i);
			char x = b.getX();
			int y = b.getY();
			if ( x == xCoord && y == yCoord ){
				res = false;
			}
			i++;
		}
		return res;
	}
	

}
