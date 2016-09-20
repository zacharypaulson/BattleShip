package BattleShip;

public class Destroyer extends Ship {
	
	int length;

	public Destroyer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public char drawShipStatusAtCell(boolean isDamaged) {
		char notDamagedCell = 'D';
		char damagedCell = 'd';
		if (isDamaged) {
			return damagedCell;
		} else {
			return notDamagedCell;
		}
	}

	@Override
	public int getLength() {
		length = 4;
		return length;
	}
	
}

