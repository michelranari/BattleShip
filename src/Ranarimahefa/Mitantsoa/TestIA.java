package Ranarimahefa.Mitantsoa;

public class TestIA {
	
public static void main(String[] args) {
		
		AIBeginner beginner = new AIBeginner();
		AIMedium medium = new AIMedium();
		GameEngine game = new GameEngine(beginner,medium);
		
		int scoreBeginner = 0;
		int scoreMedium = 0;
		
		for (int i = 0; i<10;i++){
			game.gameLoop();
			
			if (game.results() == 0){
				scoreBeginner++;
			}
			else{
				scoreMedium++;
			}
			game.restartGame();
		}
		
		System.out.println(beginner.getName() + ";"+scoreBeginner+";"+medium.getName()+";"+scoreMedium);
		
		AIBeginner beginner2 = new AIBeginner();
		AIHard hard = new AIHard();
		GameEngine game2 = new GameEngine(beginner2,hard);
		int scoreBeginner2 = 0;
		int scoreHard = 0;
		
		for (int i = 0; i<20;i++){
			game2.gameLoop();
			
			if (game2.results() == 0){
				scoreBeginner2++;
			}
			else{
				scoreHard++;
			}
			game2.restartGame();
		}
		
		System.out.println(beginner2.getName() + ";"+scoreBeginner2+";"+hard.getName()+";"+scoreHard);
		
		AIMedium medium2 = new AIMedium();
		AIHard hard2 = new AIHard();
		GameEngine game3 = new GameEngine(medium2,hard2);
		int scoreMedium2 = 0;
		int scoreHard2 = 0;
		
		for (int i = 0; i<10;i++){
			game3.gameLoop();
			
			if (game3.results() == 0){
				scoreMedium2++;
			}
			else{
				scoreHard2++;
			}
			game3.restartGame();
		}
		
		System.out.println(medium2.getName() + ";"+scoreMedium2+";"+hard2.getName()+";"+scoreHard2);
		
	}

}
