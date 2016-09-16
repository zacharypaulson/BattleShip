package BattleShip;

public class Test {
	
	public static void main( String [] args){
		int rowCount = 10;
		int colCount = 10;
		String LINE_END = System.getProperty("line.separator"); 
		
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
					boardDrawing.append(' ');
				}
			}
		}
		System.out.println(boardDrawing.toString());
	}

}
