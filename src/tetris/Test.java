package tetris;

public class Test {
	
	public static void main(String[] agrs) {
		 SquareType[][] array = new SquareType[6][10];
		 SquareType[] rowZero = array[0];
		 SquareType[] rowFive = array[5];
		 SquareType row5col9 = array[5][9];
		 array[5][9] = SquareType.EMPTY;
		 
		 System.out.println(rowZero);
		 System.out.println(row5col9);
		 for(int i = 0; i < 10; i++) {
			 System.out.println(rowFive[i]);
		 }
		 
		 for(int y = 0; y < 6; y++) {
			 for(int x = 0; x < 10; x++) {
				 System.out.println(array[y][x]);
			 }
		 }
		 
	}

}
