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
	
	public static void main(String[] args) {
		
		ArrayList<Box> box;
		box = new ArrayList<Box>();
		Box box1 = new Box('a',1);
		Box box2 = new Box('b',2);
		Box box3 = new Box('c',2);
		Box box4 = new Box('y',2);
		Box box5 = new Box('d',9);
		
		box.add(box1);
		box.add(box2);
		box.add(box3);
		box.add(box4);
		System.out.println(box.indexOf(box3));
		
	}
}
