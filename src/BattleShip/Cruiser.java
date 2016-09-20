package BattleShip;

public class Cruiser extends Ship {
	
	int length;

	public Cruiser(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	//I'm a cruiser and I should inherit a ship
	@Override
	public char drawShipStatusAtCell(boolean isDamaged) {
		char notDamagedCell = 'C';
		char damagedCell = 'c';
		if (isDamaged) {
			return damagedCell;
		} else {
			return notDamagedCell;
		}
	}
	@Override
	public int getLength() {
		length = 3;
		return length;
	}
}