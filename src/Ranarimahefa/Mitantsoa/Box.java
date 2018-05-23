package Ranarimahefa.Mitantsoa;
import java.util.ArrayList;
import java.util.Scanner;

public class Box {
	
	char x; // line
	int y; // column
	boolean touched;
	
	public Box(char x, int y){
		this.x = x;
		this.y = y;
		touched = false;
	}

	public char getX() {
		return x;
	}

	public void setX(char x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean value) {
		this.touched = value;
	}
	
}
