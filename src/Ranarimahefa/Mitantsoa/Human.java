package Ranarimahefa.Mitantsoa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Human extends Player{
	
	String Name;
	
	public Human(String name){
		this.Name = name;
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
	
	
	/* ------------------------------------ Others functions ----------------------------------*/
	
	// coordonnées du tir entrée par l'utilisateur
	public String shoot(){
		boolean checkInput;
		String coord;
		do{
			System.out.println("where do you want to shoot ? :");
			Scanner sc = new Scanner(System.in);
			coord = sc.nextLine();
			checkInput = Rules.checkCoord(coord);
			if(checkInput == false){
				System.out.println("The coordinates entered are incorrect");
			}
		}while (checkInput == false);
		return coord;
	}
	
	public boolean restart(){
		boolean res;
		Scanner sc = new Scanner(System.in);
		String choice;
		System.out.println("Do you want to restart the game ? Enter yes or no : ");
		
		do{
			choice = sc.nextLine();
		} while (!choice.equals("yes") && !choice.equals("no"));
		
		res = choice.equals("yes");
		
		return res;
	}
	
	/* ---------------------------------------- function to create and check the inputs of user  --------------------------------------------- */
	
	// creates and places ships
	public void createArmy(){
		
		System.out.printf("%s place you ship: \n", this.getName());
		
		boolean checkInput = false;
		HashMap<Integer, Integer> nbTypeShip = Rules.initNbTypeShip(); 	// HasMap indicating the number of ship placed according to their size
		int nbShip = 0;													// Total number of already placed ships
		
		while (checkInput == false || nbShip < Rules.NB_OF_SHIP ){
			
			this.displayShip();
			
			Scanner sc = new Scanner(System.in);
			System.out.printf("Enter the start coordinates of the ship n°%d: \n", nbShip + 1);
			String startCoord = sc.nextLine();
			System.out.printf("Enter the end coordinates of the ship n°%d: \n", nbShip + 1);
			String endCoord = sc.nextLine();
			
			// verification of user input
			checkInput = Rules.checkCoord(startCoord) && Rules.checkCoord(endCoord);
			if(checkInput){
				
				char XstartCoord = Character.toLowerCase(startCoord.charAt(0));
				int YstartCoord =  Integer.parseInt(startCoord.substring(1));
				char XendCoord = Character.toLowerCase(endCoord.charAt(0));
				int YendCoord = Integer.parseInt(endCoord.substring(1));
				
				// other verification
				checkInput = this.checkInputShip(XstartCoord,YstartCoord,XendCoord,YendCoord);
				if (checkInput){
					int sizeShipEntry = sizeShipEntry(XstartCoord,YstartCoord,XendCoord,YendCoord);
					boolean orientation = this.orientationShipEntry(XstartCoord,YstartCoord,XendCoord,YendCoord);
					checkInput = this.checkBoxShipOccupied(XstartCoord,YstartCoord, orientation, sizeShipEntry);
					if (checkInput){
						checkInput = this.checkSizeShip(XstartCoord,YstartCoord,sizeShipEntry,nbTypeShip);
						if (checkInput){
							// create the ship, add it to the list of the ships and decrements the number of ship of this size
							Ship ship = new Ship(startCoord,endCoord);
							this.getListShip().add(ship);
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
			else{
				System.out.println("The coordinates entered are incorrect");
			}
		}
	}
		
	
	// return true if the coordinates of the seized ship are correct, false otherwise
	public boolean checkInputShip(char XstartCoord, int YstartCoord, char XendCoord, int YendCoord){
		// check size ship
		int size = sizeShipEntry(XstartCoord,YstartCoord,XendCoord,YendCoord);
		boolean res =  size <= 5 && size > 1;
		// if the entered coordinates are in diagonal
		if ( YstartCoord != YendCoord && XstartCoord != XendCoord){
			res = false;
		}
		return res;
	}
	

	// return true if a box of the input ship is not occupied yet, false otherwise
	// - listShip is the list of the already placed ships.
	public boolean checkBoxShipOccupied(char XstartCoord, int YstartCoord, boolean orientation, int sizeShipEntry) {
		boolean res = true;
		int i = 0;
		int j = 0;
		
		if (this.getListShip().size() > 0){
			while (res == true &&  i < this.getListShip().size()){
				while (res == true && j < sizeShipEntry){
					// vertical
					if (orientation){
						res = this.getShip(i).boxIsInShip(XstartCoord,YstartCoord + j);
					}
					// horizontal
					else{
						char c = (char)(XstartCoord + j);
						res = this.getShip(i).boxIsInShip(c,YstartCoord);
					}
					j++;
				}
				j = 0;
				i++;
			}
		}
		return res;
	}
	
	
	// return true if the size of the input ship was not placed yet, false otherwise
	// - table : HasMap indicating the number of ship placed according to their size
	public boolean checkSizeShip(char XstartCoord, int YstartCoord,int sizeShipEntry , HashMap<Integer, Integer> table){
		boolean res = table.get(sizeShipEntry) > 0;
		return res;
	}
	
	// decrements the number of ship placed by the size seized
	// - sizeShipEntry : size of the input ship
	// - table : HasMap indicating the number of ship placed according to their size
	public HashMap<Integer, Integer> setNbTypeShip(int sizeShipEntry, HashMap<Integer, Integer> table){
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
	
	/* ------------------------------------ Function for display ----------------------------------*/
	
	//
	public void displayShip(){
		
		String line = new String(new char[Rules.MAP_SIZE]).replace("\0", "----");
		System.out.print("  ");
		
		for (int column = 0; column < Rules.MAP_SIZE ; column++)
        {
            System.out.print("| " + (char)('a'+column) + " ");
        }
		
		for (int row = 1; row < Rules.MAP_SIZE + 1; row++)
	    {
	        System.out.print("|\n");
	        System.out.println("   "+ line);
	        System.out.printf("%d ",row);

	        for (int column = 1 ; column < Rules.MAP_SIZE + 1; column++)
	        	
	        {
	        	String c = " ";
	        	if(this.isHitPlayer((char)('a'+column-1), row)){
	        		c = "X";
	        	}
	            System.out.print("| " + c + " ");
	        }
	    }
	    System.out.println("|");
	    System.out.println("   "+ line + "\n");
	}
	
	public void displayShoot(){
		System.out.printf("list of successful shots: %s \n", this.getShootHit());
		System.out.printf("list of failed shots: %s \n", this.getMissedShoot());
	}
	
}
