import java.util.*;

public class GameEngine {
	
	public final static int MAP_SIZE = 10;		// size of the map
	public final static int NB_OF_SHIP = 5;		// number of ships per player
	
	
	ArrayList<Player> listPlayer;	// list of player
	int pivotCurrentPlayer;			// allows to know the current player
									// 0 when it's the first player who plays and 1 when it's the second player
	
	/* ------------------------------------ Getter & Setter ----------------------------------*/
	
	public GameEngine(){
		this.listPlayer  = new ArrayList<Player>();
		this.pivotCurrentPlayer = 0;
	}
	
	public ArrayList<Player> getListPlayer() {
		return listPlayer;
	}

	public void setListPlayer(ArrayList<Player> listPlayer) {
		this.listPlayer = listPlayer;
	}
	
	public int getPivotCurrentPlayer() {
		return pivotCurrentPlayer;
	}

	public void setPivotCurrentPlayer(int value) {
		this.pivotCurrentPlayer = value;
	}
	
	public Player getPlayer(int pos){
		return this.getListPlayer().get(pos);
	}
	
	public Player getCurrentPlayer() {
		return this.getPlayer(pivotCurrentPlayer);
	}
	
	public Player getOpposingPlayer(){
		if (getPivotCurrentPlayer() == 0){
			return this.getPlayer(1);
		}else{
			return this.getPlayer(0);
		}
	}
	
	// change the current player
	public void switchCurrentPlayer(){
		if (getPivotCurrentPlayer() == 0){
			this.setPivotCurrentPlayer(1); 
		}else{
			this.setPivotCurrentPlayer(0); 
		}
	}
	
	/* ---------------------------------------- Game --------------------------------------------- */
	
	// Create players and his ships
	public void initializeGame(){
		
		for (int i = 0; i<2; i++){
			//System.out.println("Enter your name :");
			//Scanner sc = new Scanner(System.in);
			//String name = sc.nextLine();
			Player p = new Player(/*name*/);
			//System.out.printf("%s place his boats: \n", name);
			System.out.println("Place your ship: ");
			p.createArmy();
			this.getListPlayer().add(p);
		}
		
	}

	public void PlayerVersusPlayer(){
		
		this.initializeGame();

		while (getPlayer(0).hasLost()== false && getPlayer(1).hasLost() == false){
			
			System.out.printf("It's %s's turn to play : \n", this.getCurrentPlayer().getName());
			System.out.printf("list of successful shots: %s \n", this.getCurrentPlayer().toStringShootHit());
			System.out.printf("list of failed shots: %s \n", this.getCurrentPlayer().toStringMissedShoot());
			System.out.println("where do you want to shoot ? :");
			Scanner sc = new Scanner(System.in);
			String coord = sc.nextLine();
			char xCoordShoot = coord.charAt(0);
			int yCoordShoot =  Integer.parseInt(coord.substring(1));
			
			if (this.checkCoord(xCoordShoot,yCoordShoot)){
				// if the shot hit an opponent's ship
				if(this.getOpposingPlayer().isHitPlayer(xCoordShoot, yCoordShoot)){
					System.out.println("BOOOM ! Hit !");
					Ship shipHit = this.getOpposingPlayer().getHitShip(xCoordShoot, yCoordShoot);
					Box boxHit = shipHit.getHitBox(xCoordShoot, yCoordShoot);
					boxHit.setTouched(true);
					this.getCurrentPlayer().getShootHit().add(coord);
					if(shipHit.isDestroyed()){
						System.out.println("The ship has sunk !");
					}
				}
				else{
					System.out.println("Missed ! ");
					this.getCurrentPlayer().getMissedShoot().add(coord);
				}
			}
			else{
				System.out.println("The coordinates entered are incorrect");
			}
			
			 this.switchCurrentPlayer();	
		}
		
		this.results();
		
	}
	
	//
	public void results(){
		Player winner;
		if (this.getPlayer(0).hasLost() == true){
			winner = this.getPlayer(1);
		}else{
			winner = this.getPlayer(0);
		}
		System.out.println("End of the game !");
		//System.out.printf("%s Wins", winner.getName());
	}
	
	
	
	/* ---------------------------------------- function to check the inputs of user  --------------------------------------------- */
	
	// return true if the coordinates X and Y are well entered, false otherwise
		public boolean checkCoord(char xCoord, int yCoord){
			boolean res = true;
			if ( yCoord < 1 || yCoord > GameEngine.MAP_SIZE || xCoord < 'a' ||  xCoord >= 'a' + GameEngine.MAP_SIZE){
				res = false;
			}
			return res;
		}

	
	
	public static void main(String[] args) {
		GameEngine game = new GameEngine();
		game.PlayerVersusPlayer();;
	}

}



