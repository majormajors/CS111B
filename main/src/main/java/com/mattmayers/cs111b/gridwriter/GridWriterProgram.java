package com.mattmayers.cs111b.gridwriter;

public class GridWriterProgram {
	
	public static void main(String[] args) {
		GridWriter gw = new GridWriter(40, 50);
		
		gw.add(new MyCircle(10, 10, 9));
		gw.add(new MyCircle(25, 20, 12));
		gw.add(new MyCircle(25, 20, 5));

		gw.add(new MyRectangle(25, 25, 20, 15));
		gw.add(new MyRectangle(5, 5, 3, 4));
		gw.add(new MyRectangle(40, 0, 10, 10));

		gw.add(new MySquare(30, 30, 10));

		try {
			gw.get(7); // should throw an exception
		} catch (IndexOutOfBoundsException e) {
			System.out.println("ERROR: " + e.getMessage());
		}

		gw.display();
	}
	
}