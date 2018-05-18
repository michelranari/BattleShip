import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Player {
	
	String Name;
	ArrayList<Ship> listShip;
	ArrayList<String> shootHit;		// shots that hit the enemy
	ArrayList<String> missedShoot;	// failed shoot
	
	public Player(){
		//this.Name = name;
		this.listShip = new ArrayList<Ship>();
		this.shootHit = new ArrayList<String>();
		this.missedShoot =  new ArrayList<String>();
	}
	
	/* ------------------------------------ Getter & Setter ----------------------------------*/
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

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
	
	/* ------------------------------------ Others functions ----------------------------------*/
	
	// return the list of failed shots in form of string
		public String toStringMissedShoot(){
			String res = "[ ";
			for (int i = 0; i < this.getMissedShoot().size() - 1; i++){
				String coord = this.getAMissedShoot(i);
				res = res + coord + ", ";
			}
			res = this.getAMissedShoot( this.getMissedShoot().size() - 1) + "]";
			return res;
		}
		
		// return the list of successful shots in form of string
		public String toStringShootHit(){
			String res = "[ ";
			for (int i = 0; i < this.getShootHit().size() - 1; i++){
				String coord = this.getAShootHit(i);
				res = res + coord + ", ";
			}
			res = this.getAShootHit(this.getShootHit().size() - 1) + "]";
			return res;
		}
		
		// return the ship hit by the missile of x and y coordinates
		public Ship getHitShip(char xCoord, int yCoord){
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
		
		// return true if a ship of this player is hited by the missile of x and y coordinates, false otherwise
		public boolean isHitPlayer(char xCoord, int yCoord){
			boolean res = false;
			int i = 0;
			while (res == false && i < this.getListShip().size()){
				Ship s = this.getShip(i);
				res = s.isHit(xCoord, yCoord);
				i++;
			}
			return res;
		}
		
		// return true if the player lost the game, i.e. if all these boats were sunk. False otherwise
		public boolean hasLost(){
			boolean res = true;
			int i = 0;
			while (res == true && i < this.getListShip().size()){
				Ship s = this.listShip.get(i);
				if ( s.isDestroyed() == false){
					res = false;
				}
				i++;
			}
			return res;
		}
	
	/* ---------------------------------------- function to create and check the inputs of user  --------------------------------------------- */
	
	// creates and places ships
	public void createArmy(){
		
		boolean checkInput = false;
		Hashtable<Integer, Integer> nbTypeShip = initNbTypeShip(); 	// HasMap indicating the number of ship placed according to their size
		int nbShip = 0;												// Total number of already placed ships
		
		while (checkInput == false || nbShip < GameEngine.NB_OF_SHIP ){
			
			Scanner sc = new Scanner(System.in);
			System.out.printf("Enter the start coordinates of the ship n°%d: \n", nbShip + 1);
			String startCoord = sc.nextLine();
			System.out.printf("Enter the end coordinates of the ship n°%d: \n", nbShip + 1);
			String endCoord = sc.nextLine();

			char XstartCoord = Character.toLowerCase(startCoord.charAt(0));
			int YstartCoord =  Integer.parseInt(startCoord.substring(1));
			char XendCoord = Character.toLowerCase(startCoord.charAt(0));
			int YendCoord = Integer.parseInt(endCoord.substring(1));
			
			// verification of user input
			checkInput = this.checkInputShip(XstartCoord,YstartCoord,XendCoord,YendCoord);
			if (checkInput){
				checkInput = this.checkBoxShipOccupied(XstartCoord,YstartCoord,XendCoord, YendCoord);
				if (checkInput){
					checkInput = this.checkSizeShip(XstartCoord,YstartCoord,XendCoord,YendCoord,nbTypeShip);
					if (checkInput){
						// create the ship, add it to the list of the ships and decrements the number of ship of this size
						Ship ship = new Ship(startCoord,endCoord);
						this.getListShip().add(ship);
						int sizeShipEntry = sizeShipEntry(XstartCoord,YstartCoord,XendCoord,YendCoord);
						nbTypeShip = setNbTypeShip(sizeShipEntry,nbTypeShip);
						nbShip++;
					}
					else{
						System.out.println("The maximum number of ship of this size has been reached");
					}
				}else{
					System.out.println("A ship is already located on its coordinates");
				}
			}
			else{
				System.out.println("The coordinates entered are incorrect");
			}
		}
		
	}
		
	// return true if the coordinates X and Y are well entered, false otherwise
	public boolean checkCoord(char xCoord, int yCoord){
		boolean res = true;
		if ( yCoord < 1 || yCoord > GameEngine.MAP_SIZE || xCoord < 'a' ||  xCoord >= 'a' + GameEngine.MAP_SIZE){
			res = false;
		}
		return res;
	}


	// return true if the coordinates of the seized ship are correct, false otherwise
	public boolean checkInputShip(char XstartCoord, int YstartCoord, char XendCoord, int YendCoord){
		boolean res;
		res = this.checkCoord( XstartCoord,YstartCoord);
		res = this.checkCoord(XendCoord,YendCoord);
		
		// if the entered coordinates are in diagonal
		if ( YstartCoord != YendCoord && XstartCoord != XendCoord){
			res = false;
		}
		return res;
	}
	

	// return true if a box of the input ship is not occupied yet, false otherwise
	// - listShip is the list of the already placed ships.
	public boolean checkBoxShipOccupied(char XstartCoord, int YstartCoord, char XendCoord, int YendCoord/*, ArrayList<Ship> listShip*/) {
		boolean res = true;
		boolean orientation = this.orientationShipEntry(XstartCoord,YstartCoord,XendCoord,YendCoord);
		int sizeShipEntry = this.sizeShipEntry(XstartCoord,YstartCoord,XendCoord,YendCoord);
		int i = 0;
		int j = 0;
		
		if (this.getListShip().size() > 0){
			while (res == true &&  i < this.getListShip().size()){
				while (res == true && j < sizeShipEntry){
					// vertical
					if (orientation){
						res = this.getListShip().get(i).boxIsInShip(XstartCoord,YstartCoord + j);
					}
					// horizontal
					else{
						char c = (char)(XstartCoord + j);
						res = this.getListShip().get(i).boxIsInShip(c,YstartCoord);
					}
					j++;
				}
				j = 0;
				i++;
			}
		}
		return res;
	}
	
	// Init the Hastable indicating the number of ship placed according to their size
	// first element is the size and second is the number of ship
	public Hashtable<Integer, Integer> initNbTypeShip(){
		Hashtable<Integer, Integer> nbTypeShip = new Hashtable<Integer, Integer>();
		nbTypeShip.put(5,1);
		nbTypeShip.put(4,1);
		nbTypeShip.put(3,2);
		nbTypeShip.put(2,1);
		return nbTypeShip;
	}
	
	// return true if the size of the input ship was not placed yet, false otherwise
	// - table : HasMap indicating the number of ship placed according to their size
	public boolean checkSizeShip(char XstartCoord, int YstartCoord, char XendCoord, int YendCoord, Hashtable<Integer, Integer> table){
		boolean res = true;
		int sizeShipEntry = sizeShipEntry(XstartCoord,YstartCoord,XendCoord,YendCoord);
		res = table.get(sizeShipEntry) > 0;
		return res;
	}
	
	// decrements the number of ship placed by the size seized
	// - sizeShipEntry : size of the input ship
	// - table : HasMap indicating the number of ship placed according to their size
	public Hashtable<Integer, Integer> setNbTypeShip(int sizeShipEntry, Hashtable<Integer, Integer> table){
		table.put(sizeShipEntry, table.get(sizeShipEntry) - 1);
		return table;
	}
	
	// determine orientation of the input ship
	// return true if vertical, otherwise false
	public boolean orientationShipEntry(char XstartCoord, int YstartCoord, char XendCoord, int YendCoord){
		if (XstartCoord == XendCoord){
			return true;
		}
		else{
			return false;
		}
	}
		
	// determine the size of the input ship
	public int sizeShipEntry(char XstartCoord, int YstartCoord, char XendCoord, int YendCoord){
		boolean orientation = orientationShipEntry (XstartCoord,YstartCoord,XendCoord,YendCoord);
		if (orientation){
			return (YendCoord -  YstartCoord) + 1;
		}
		else{
			return  (XendCoord -  XstartCoord) + 1;
		}
	}
	
}
