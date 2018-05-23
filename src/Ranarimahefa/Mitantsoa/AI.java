package Ranarimahefa.Mitantsoa;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AI extends Player {
	
	public String getName(){
		return "AI";
	}

	// generate a random coordinate
	public String generateCoord(){
		Random rand = new Random(); 
		int randomNum1 = rand.nextInt(10);
		char xCoord = (char)('a' + randomNum1);
		int yCoord = rand.nextInt(10) + 1;
		String coord = "" + xCoord + yCoord;
		return coord;
	}
	
	public String shoot(){
		return "";
	}
	
	// return true if the coordinate exist in the array of missed shoot
	public boolean existInMissed(String coord){
		boolean res = false;
		int i = 0;
		while (res == false && i<this.getMissedShoot().size() ){
			if (this.getAMissedShoot(i).equals(coord)){
				res = true;
			}
			i++;
		}
		return res;
		//return this.getMissedShoot().contains(coord);
	}
	
	// return true if the coordinate exist in the array of succesful shoot
	public boolean existInSuccessfulShoot(String coord){
		boolean res = false;
		int i = 0;
		while (res == false && i<this.getShootHit().size() ){
			if (this.getAShootHit(i).equals(coord)){
				res = true;
			}
			i++;
		}
		return res;
		//return this.shootHit.contains(coord);
	}
	
	public boolean restart(){
		return true;
	}
	
	/* ---------------------------------------- function to create army  --------------------------------------------- */
	
	public void createArmy(){
		
		String Coord;
		boolean checkInput;
		HashMap<Integer, Integer> nbTypeShip = Rules.initNbTypeShip();
		
		// browse the HasMap
		for (Entry<Integer, Integer> entry : nbTypeShip.entrySet()) {
		    int size = entry.getKey();
		    int nb = entry.getValue();
		    int i;
		    for( i = 0; i < nb ; i++ ){
		    	do{
			    	Coord = this.generateCoord();
			    	char XstartCoord = Character.toLowerCase(Coord.charAt(0));
					int YstartCoord =  Integer.parseInt(Coord.substring(1));
					boolean orientation = randomOrientation();
					String endCoord = getEndCoord(Coord, orientation,size);
					checkInput = checkBoxShipOccupied(XstartCoord,YstartCoord,orientation,size);
					if (checkInput){
						Ship ship = new Ship(Coord ,endCoord);
						System.out.println("add ship:" + XstartCoord + YstartCoord + " " + size + " " + orientation);
						this.getListShip().add(ship);
					}
		    	} while(checkInput == false);
		    }
		}
		
	}
	
	// return true if a box of the input ship is not occupied yet, false otherwise
	// - listShip is the list of the already placed ships.
	public boolean checkBoxShipOccupied(char XstartCoord, int YstartCoord, boolean orientation, int sizeShipEntry ){
		String coord;
		boolean res = true;
		int i = 0;
		int j = 0;
		
		while (res &&  i < this.getListShip().size()){
			while (res && j < sizeShipEntry){
				// vertical
				if (orientation){
					coord= "" + XstartCoord + (YstartCoord + j);
					res = Rules.checkCoord(coord);
					if (res && this.getListShip().size() > 0){
						res = this.getListShip().get(i).boxIsInShip(XstartCoord,YstartCoord + j);
					}
				}
				// horizontal
				else{
					coord = "" + (char)(XstartCoord + j) + YstartCoord;
					res = Rules.checkCoord(coord);
					if (res &&this.getListShip().size() > 0){
						res = this.getListShip().get(i).boxIsInShip((char)(XstartCoord + j),YstartCoord);
					}
				}
				j++;
			}
			j = 0;
			i++;
		}
		
		return res;
	}
	
	// generate a random orientation for the ship. true correspond to vertical and false to horizontal
		public boolean randomOrientation(){
			boolean res = false;
			int choice  = ThreadLocalRandom.current().nextInt(2);
			if (choice == 0){
				res = true;
			}
			return res;
		}
		
	// return end coordinate of a ship in function to his start coordinate, size and orientation
	public String getEndCoord(String startCoord, boolean orientation, int size){
		String endCoord = "";
		char XstartCoord = Character.toLowerCase(startCoord.charAt(0));
		int YstartCoord =  Integer.parseInt(startCoord.substring(1));
		
		// vertical
		if (orientation){
			endCoord = endCoord + XstartCoord + (YstartCoord + size - 1);
		}
		// horizontal
		else{
			char c = (char)(XstartCoord + size - 1);
			endCoord = endCoord + c + YstartCoord ;
		}
		return endCoord;
	}
	
	public void displayShoot(){}
	
}
