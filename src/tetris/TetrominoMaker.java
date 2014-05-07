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
		
		SquareType[][] tetromino = new SquareType[][]{
				{ SquareType.EMPTY,    	SquareType.I,     SquareType.EMPTY,     SquareType.EMPTY },
				{ SquareType.EMPTY,     SquareType.I,	  SquareType.EMPTY,     SquareType.EMPTY },
				{ SquareType.EMPTY,     SquareType.I,     SquareType.EMPTY,     SquareType.EMPTY },
				{ SquareType.EMPTY,     SquareType.I,     SquareType.EMPTY,     SquareType.EMPTY },
		};
		/*
		SquareType[][] tetromino = new SquareType[][]{
				{ SquareType.EMPTY,    	SquareType.EMPTY,     SquareType.EMPTY,     SquareType.EMPTY },
				{ SquareType.EMPTY,     SquareType.EMPTY,	  SquareType.EMPTY,     SquareType.EMPTY },
				{ SquareType.I,     SquareType.I,     SquareType.I,     SquareType.I },
				{ SquareType.EMPTY,     SquareType.EMPTY,     SquareType.EMPTY,     SquareType.EMPTY },
		};
		*/
		return tetromino;
	}
	
	/**
	 * Creates an O shaped tetromino 
	 * @return
	 */
	private SquareType[][] createO() {
		SquareType[][] tetromino = new SquareType[][]{
				{ SquareType.O,     	SquareType.O },
				{ SquareType.O,    		SquareType.O }
		};
		return tetromino;
	}
	
	/**
	 * Creates an T shaped tetromino 
	 * @return
	 */
	private SquareType[][] createT() {
		SquareType[][] tetromino = new SquareType[][]{
				{ SquareType.EMPTY,     SquareType.T, 			SquareType.EMPTY },
				{ SquareType.T,     	SquareType.T,     		SquareType.EMPTY },
				{ SquareType.EMPTY,     SquareType.T,  			SquareType.EMPTY }
		};	
		return tetromino;
	}
	
	/**
	 * Creates an S shaped tetromino 
	 * @return
	 */
	private SquareType[][] createS() {
		SquareType[][] tetromino = new SquareType[][]{
				{ SquareType.EMPTY,     SquareType.S, 		SquareType.EMPTY },
				{ SquareType.S,     	SquareType.S,     	SquareType.EMPTY },
				{ SquareType.S,     	SquareType.EMPTY,   SquareType.EMPTY }
		};
		return tetromino;
	}
	
	/**
	 * Creates an Z shaped tetromino 
	 * @return
	 */
	private SquareType[][] createZ() {
		SquareType[][] tetromino = new SquareType[][]{
				{ SquareType.Z,     		SquareType.EMPTY, 	SquareType.EMPTY },
				{ SquareType.Z,     		SquareType.Z,   	SquareType.EMPTY },
				{ SquareType.EMPTY,     	SquareType.Z,   	SquareType.EMPTY }		
		};
		return tetromino;
	}
	
	/**
	 * Creates an J shaped tetromino 
	 * @return
	 */
	private SquareType[][] createJ() {
		SquareType[][] tetromino = new SquareType[][]{
				{ SquareType.J,     	SquareType.J, 		SquareType.EMPTY },
				{ SquareType.EMPTY,     SquareType.J,     	SquareType.EMPTY },
				{ SquareType.EMPTY,     SquareType.J,   	SquareType.EMPTY },			
		};
		return tetromino;
	}
	/**
	 * Creates an L shaped tetromino 
	 * @return
	 */
	private SquareType[][] createL() {
		SquareType[][] tetromino = new SquareType[][]{
				{ SquareType.EMPTY,     SquareType.L,     	SquareType.EMPTY },
				{ SquareType.EMPTY,     SquareType.L,   	SquareType.EMPTY },
				{ SquareType.L,     	SquareType.L,    	SquareType.EMPTY }				
		};
		return tetromino;
	}
}
