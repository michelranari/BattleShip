package Ranarimahefa.Mitantsoa;
import java.util.HashMap;


public class Rules {
	
	public final static int MAP_SIZE = 10;		// size of the map
	public final static int NB_OF_SHIP = 5;		// number of ships per player
	
	// return true if the coordinates X and Y are well entered, false otherwise
	public static boolean checkCoord(String coord){
		boolean res = coord.length() > 1 && coord.length() < 4 ;
		if (res){
			char xCoord = Character.toLowerCase(coord.charAt(0));
			String yCoord = coord.substring(1);
			res = isInteger(yCoord);
			if (res){
				int yCoord2 =  Integer.parseInt(yCoord);
				if ( yCoord2 < 1 || yCoord2 > MAP_SIZE || xCoord < 'a' ||  xCoord > 'a' + (MAP_SIZE - 1)){
					res = false;
				}
			}
		}
		return res;
	}
	
	// to check if the string is a integer
	public static boolean isInteger(String str) {
	    int length = str.length();
	    int i;
	    for (i = 0; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}
	
	// Init the Hastable indicating the number of ship placed according to their size
	// first element is the size and second is the number of ship
		public static HashMap<Integer, Integer> initNbTypeShip(){
			HashMap<Integer, Integer> nbTypeShip = new HashMap<Integer, Integer>();
			nbTypeShip.put(5,1);
			nbTypeShip.put(4,1);
			nbTypeShip.put(3,2);
			nbTypeShip.put(2,1);
			return nbTypeShip;
		}

}
