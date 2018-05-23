package Ranarimahefa.Mitantsoa;
import java.util.*;

public class GameEngine {
	
	ArrayList<Player> listPlayer;	// list of player
	int playerBeginner;				// allows to know which players starts first
	int pivotCurrentPlayer;			// allows to know the current player
									// 0 when it's the first player who plays and 1 when it's the second player
	
	/* ------------------------------------ Getter & Setter ----------------------------------*/
	
	public GameEngine(Player p1,Player p2){
		this.listPlayer  = new ArrayList<Player>();
		this.listPlayer.add((p1));
		this.listPlayer.add((p2));
		this.pivotCurrentPlayer = 0;
		this.playerBeginner = 0;
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
	
	public int getPlayerBeginner() {
		return playerBeginner;
	}

	public void setPlayerBeginner(int playerBeginner) {
		this.playerBeginner = playerBeginner;
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
		this.getCurrentPlayer().createArmy();
		this.getOpposingPlayer().createArmy();
	}

	public void gameLoop(){
		
		this.initializeGame();

		while (getPlayer(0).hasLost()== false && getPlayer(1).hasLost() == false){
			
			
			System.out.printf("It's %s's turn to play : \n", this.getCurrentPlayer().getName());
			this.getCurrentPlayer().displayShoot();
			String coord = this.getCurrentPlayer().shoot();
			
			char xCoordShoot = coord.charAt(0);
			int yCoordShoot =  Integer.parseInt(coord.substring(1));
			// if the shot hit an opponent's ship
			if(this.getOpposingPlayer().isHitPlayer(xCoordShoot, yCoordShoot)){
				//System.out.println("BOOOM ! Hit !");
				Ship shipHit = this.getOpposingPlayer().getHitShip(xCoordShoot, yCoordShoot);
				int indexShipHit = this.getOpposingPlayer().getIndexHitShip(xCoordShoot, yCoordShoot);
				this.getOpposingPlayer().setHit(xCoordShoot, yCoordShoot);
				this.getCurrentPlayer().getShootHit().add(coord);
				if(shipHit.isDestroyed()){
					System.out.println("The ship has sunk !");
					this.getOpposingPlayer().listShip.remove(indexShipHit);
				}
			}
			else{
				this.getCurrentPlayer().getMissedShoot().add(coord);
			}
			
			
			 this.switchCurrentPlayer();	
		}
		
	}
	
	
	public int results(){
		int res;
		Player winner;
		if (this.getPlayer(0).hasLost() == true){
			winner = this.getPlayer(1);
			res = 1;
		}else{
			winner = this.getPlayer(0);
			res = 0;
		}
		System.out.printf("%s Wins\n", winner.getName());
		return res;
	}
	
	// empty all elements of the game for a new game
	public void restartGame(){
		if(this.playerBeginner == 0){
			this.setPivotCurrentPlayer(1);
			this.setPlayerBeginner(1);
		}
		else{
			this.setPivotCurrentPlayer(0);
			this.setPlayerBeginner(0);
		}
		
		this.getPlayer(0).resetPlayer();
		this.getPlayer(1).resetPlayer();
	}
	
	public static void main(String[] args) {
		
		
	}

}



