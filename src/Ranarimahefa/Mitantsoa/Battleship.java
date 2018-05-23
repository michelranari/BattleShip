package Ranarimahefa.Mitantsoa;

import java.util.Scanner;

public class Battleship {
	
	public static void main(String[] args) {
		
		GameEngine game;
		Player p1 = null;
		Player p2  = null;
		
		System.out.println("What game mode do you want to play  ? Enter :");
		System.out.println(" 1 - Human Vs Human ");
		System.out.println(" 2 - Human Vs AI ");
		Scanner sc = new Scanner(System.in);
		String choice;
		
		do{
			choice = sc.nextLine();
		} while (!choice.equals("1") && !choice.equals("2"));
		
		String name;
		System.out.println("Player N°1 enter your name :");
		name = sc.nextLine();
		p1 = new Human(name);
		
		if(choice.equals("1")){
			System.out.println("Player N°2 enter your name :");
			name = sc.nextLine();
			p2 = new Human(name);
		}
		else{
			System.out.println("You want to play against what level of intelligence ? Enter:");
			System.out.println(" 1 - Level Beginner ");
			System.out.println(" 2 - Level Medium ");
			System.out.println(" 3 - Level Hard ");
			
			do{
				choice = sc.nextLine();
			} while (!choice.equals("1") && !choice.equals("2") && !choice.equals("3"));
			
			switch(choice) {
			  case "1":
				  p2 = new AIBeginner();
			    break;
			  case "2":
				  p2 = new AIMedium();
			    break;
			  case "3":
				  System.out.println("cas 3");
				  p2 = new AIHard();
			  default:
				  p2 = new AIHard();
			}
		}
		
		game = new GameEngine(p1,p2);
		game.gameLoop();
		game.results();
		
		if(p1.restart()){
			game.restartGame();
			game.gameLoop();
		}
		
	}

}
