package BattleShip;
import java.util.ArrayList;

public class GameBoard
{
	int rowCount = 10;
	int colCount = 10;
	
	final String LINE_END = System.getProperty("line.separator"); 
	
	ArrayList< ArrayList< Cell > > cells;
	ArrayList< Ship > myShips = new ArrayList<Ship>();
	
	public GameBoard( int rowCount, int colCount )
	{
		this.rowCount = rowCount;
		this.colCount = colCount;
		
		//create the 2D array of cells
		cells = new ArrayList<ArrayList<Cell>>();  
		for (int i = 0; i < rowCount; i++) {
			cells.add(new ArrayList<Cell>());
			for (int j = 0; j < colCount; j++) {
				cells.get(i).add(new Cell());
			}
		}
	}
	
	public String draw()
	{
		StringBuilder boardDrawing = new StringBuilder();
		for(int i = 0; i <= rowCount + 1; i ++) {
			for(int j = 0; j <= colCount + 1; j++) {
				if((i==0 && j == 0)||(i==0 && j==colCount+1)||(i==rowCount+1 && j == 0) || (i==rowCount+1 && j==colCount+1)){
					boardDrawing.append('+');
					if(j==colCount+1) {
						boardDrawing.append(LINE_END);
					}
				} else if (i == 0 || i == rowCount+1) {
					boardDrawing.append('-');
				} else if (j == 0 || j == colCount+1) {
					boardDrawing.append('|');
					if(j==colCount+1) {
						boardDrawing.append(LINE_END);
					}
				} else {
					boardDrawing.append(cells.get(i-1).get(j-1).draw()); //Needs to get the (i,j) cell in the array list... don't know if this piece works
				}
			}
		}
		return boardDrawing.toString();
		//draw the entire board... I'd use a StringBuilder object to improve speed
		//remember - you must draw one entire row at a time, and don't forget the
		//pretty border...
	}
	
	//add in a ship if it fully 1) fits on the board and 2) doesn't collide w/
	//an existing ship.
	//Returns true on successful addition; false, otherwise
	public boolean addShip( Ship s , Position sternLocation, HEADING bowDirection )
	{
		boolean shipAdded = false;
		ArrayList<Cell> shipPos = new ArrayList<Cell>();
		if(sternLocation.x < 0 || sternLocation.x >= rowCount || sternLocation.y < 0 || sternLocation.y >= colCount) {
			System.out.println("The ship has not been placed on the board");
			return shipAdded;
		}
		if(bowDirection == HEADING.WEST) {
			if(sternLocation.y - s.getLength() < 0) {
				System.out.println("The ship ends off of the board");
				return shipAdded;
			}
			for(int i = 0; i < s.getLength(); i++) {
				if(cells.get(sternLocation.y).get(sternLocation.x-i).getShip() != null) {
					return shipAdded;
				} else {
					shipPos.add(cells.get(sternLocation.y).get(sternLocation.x-i));
				}
			}
			shipAdded = true;
			for(int j = 0; j < s.getLength(); j++) {
				cells.get(sternLocation.y).get(sternLocation.x-j).setShip(s);
			}
			s.setPosition(shipPos);
			return shipAdded;
		}
		if(bowDirection == HEADING.EAST) {
			if(sternLocation.y + s.getLength() > rowCount - 1) {
				System.out.println("The ship ends off of the board");
				return shipAdded;
			}
			for(int i = 0; i < s.getLength(); i++) {
				if(cells.get(sternLocation.y).get(sternLocation.x+i).getShip() != null) {
					return shipAdded;
				} else {
					shipPos.add(cells.get(sternLocation.y).get(sternLocation.x+i));
				}
			}
			shipAdded = true;
			for(int j = 0; j < s.getLength(); j++) {
				cells.get(sternLocation.y).get(sternLocation.x+j).setShip(s);
			}
			s.setPosition(shipPos);
			return shipAdded;
		}
		if(bowDirection == HEADING.NORTH){
			if(sternLocation.x - s.getLength() < 0) {
				System.out.println("The ship ends off of the board");
				return shipAdded;
			}
			for(int i = 0; i < s.getLength(); i++) {
				if(cells.get(sternLocation.y-i).get(sternLocation.x).getShip() != null) {
					return shipAdded;
				} else {
					shipPos.add(cells.get(sternLocation.y-i).get(sternLocation.x));
				}
			}
			shipAdded = true;
			for(int j = 0; j < s.getLength(); j++) {
				cells.get(sternLocation.y-j).get(sternLocation.x).setShip(s);
			}
			s.setPosition(shipPos);
			return shipAdded;
		}
		if(bowDirection == HEADING.SOUTH) {
			if(sternLocation.x + s.getLength() > rowCount - 1) {
				System.out.println("The ship ends off of the board");
				return shipAdded;
			}
			for(int i = 0; i < s.getLength(); i++) {
				if(cells.get(sternLocation.y+i).get(sternLocation.x).getShip() != null) {
					return shipAdded;
				} else {
					shipPos.add(cells.get(sternLocation.y+i).get(sternLocation.x));
				}
			}
			shipAdded = true;
			for(int j = 0; j < s.getLength(); j++) {
				cells.get(sternLocation.y+j).get(sternLocation.x).setShip(s);
			}
			s.setPosition(shipPos);
			return shipAdded;
		}
		else {
			System.out.println("Did not match with a heading... Something went wrong PGB");
			return shipAdded;
		}
	}
	
	//Returns A reference to a ship, if that ship was struck by a missle.
	//The returned ship can then be used to print the name of the ship which
	//was hit to the player who hit it.
	//Ensure you handle missiles that may fly off the grid
	public Ship fireMissle( Position coordinate )
	{
		if(coordinate.x < 0 || coordinate.x >= rowCount || coordinate.y < 0 || coordinate.y >= colCount) {
			System.out.println("Missile was fired off of the board");
			return null;
		} else if (cells.get(coordinate.y).get(coordinate.x).getShip() != null) {
			cells.get(coordinate.y).get(coordinate.x).hasBeenStruckByMissile(true);
			return cells.get(coordinate.y).get(coordinate.x).ship;
		} else {
			System.out.println("Did not hit a ship");
			return null;
		}		
	}
	
	//Here's a simple driver that should work without touching any of the code below this point
	public static void main( String [] args )
	{
		System.out.println( "Hello World" );
		GameBoard b = new GameBoard( 10, 10 );	
		System.out.println( b.draw() );
		
		Ship s = new Cruiser( "Cruiser" );
		if( b.addShip(s, new Position(3,6), HEADING.WEST ) )
			System.out.println( "Added " + s.getName() + "Location is " );
		else
			System.out.println( "Failed to add " + s.getName() );
		
		s = new Destroyer( "Vader" );
		if( b.addShip(s, new Position(3,5), HEADING.NORTH ) )
			System.out.println( "Added " + s.getName() + "Location is " );
		else
			System.out.println( "Failed to add " + s.getName() );
		
		System.out.println( b.draw() );
		
		b.fireMissle( new Position(3,5) );
		System.out.println( b.draw() );
		b.fireMissle( new Position(3,4) );
		System.out.println( b.draw() );
		b.fireMissle( new Position(3,3) );
		System.out.println( b.draw() );
		
		b.fireMissle( new Position(0,6) );
		b.fireMissle( new Position(1,6) );
		b.fireMissle( new Position(2,6) );
		b.fireMissle( new Position(3,6) );
		System.out.println( b.draw() );
		
		b.fireMissle( new Position(6,6) );
		System.out.println( b.draw() );
	}

}
