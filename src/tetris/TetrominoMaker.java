package tetris;

/**
 *
 * @author Daniel
 *
 */
public class TetrominoMaker {
	
	public TetrominoMaker() {
	}
	
	/**
	 * 
	 * @return
	 */
	public int getNumberOfTypes() {
		return SquareType.values().length;
	}
	
	/**
	 * Returns a Polyomino based on a specified tetromino
	 * Returns null if no tetromino was created, it's considered an error
	 * @param n
	 * @return
	 */
	public Poly getPoly(int n) {
		SquareType[][] tetromino = null;
		switch (SquareType.values()[n]) {
			case EMPTY:
				System.err.println("EMPTY is not an tetromino");
				break;
			case I:
				tetromino = createI();
				break;
			case O:
				tetromino = createO();
				break;
			case T:
				tetromino = createT();
				break;	
			case S:
				tetromino = createS();
				break;	
			case J:
				tetromino = createJ();
				break;	
			case L:
				tetromino = createL();
				break;
			case Z:
				tetromino = createZ();
				break;	
			case OUTSIDE:
				System.err.println("OUTSIDE is not an tetromino");
				break;
		}
		
		return new Poly(tetromino);
	}
	
	/**
	 * Creates an I shaped tetromino 
	 * @return
	 */
	private SquareType[][] createI() {
		SquareType[][] tetromino = new SquareType[4][4];
		tetromino[0][1] = SquareType.I;
		tetromino[1][1] = SquareType.I;
		tetromino[2][1] = SquareType.I;
		tetromino[3][1] = SquareType.I;
		return tetromino;
	}
	
	/**
	 * Creates an O shaped tetromino 
	 * @return
	 */
	private SquareType[][] createO() {
		SquareType[][] tetromino = new SquareType[2][2];
		tetromino[0][0] = SquareType.O;
		tetromino[0][1] = SquareType.O;
		tetromino[1][0] = SquareType.O;
		tetromino[1][1] = SquareType.O;
		return tetromino;
	}
	
	/**
	 * Creates an T shaped tetromino 
	 * @return
	 */
	private SquareType[][] createT() {
		SquareType[][] tetromino = new SquareType[3][3];
		tetromino[0][1] = SquareType.T;
		tetromino[1][0] = SquareType.T;
		tetromino[1][1] = SquareType.T;
		tetromino[1][2] = SquareType.T;
		return tetromino;
	}
	
	/**
	 * Creates an S shaped tetromino 
	 * @return
	 */
	private SquareType[][] createS() {
		SquareType[][] tetromino = new SquareType[3][3];
		tetromino[0][1] = SquareType.S;
		tetromino[0][2] = SquareType.S;
		tetromino[1][0] = SquareType.S;
		tetromino[1][1] = SquareType.S;
		return tetromino;
	}
	
	/**
	 * Creates an Z shaped tetromino 
	 * @return
	 */
	private SquareType[][] createZ() {
		SquareType[][] tetromino = new SquareType[3][3];
		tetromino[0][0] = SquareType.Z;
		tetromino[0][1] = SquareType.Z;
		tetromino[1][1] = SquareType.Z;
		tetromino[1][2] = SquareType.Z;
		return tetromino;
	}
	
	/**
	 * Creates an J shaped tetromino 
	 * @return
	 */
	private SquareType[][] createJ() {
		SquareType[][] tetromino = new SquareType[3][3];
		tetromino[0][0] = SquareType.J;
		tetromino[1][0] = SquareType.J;
		tetromino[1][1] = SquareType.J;
		tetromino[1][2] = SquareType.J;
		return tetromino;
	}
	/**
	 * Creates an L shaped tetromino 
	 * @return
	 */
	private SquareType[][] createL() {
		SquareType[][] tetromino = new SquareType[3][3];
		tetromino[0][2] = SquareType.L;
		tetromino[1][0] = SquareType.L;
		tetromino[1][1] = SquareType.L;
		tetromino[1][2] = SquareType.L;
		return tetromino;
	}
}
