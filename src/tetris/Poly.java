package tetris;

public class Poly {
	
	private SquareType[][] poly;
	
	/**
	 * Create a new poly
	 * @param poly
	 */
	public Poly(SquareType[][] poly) {
		this.poly = poly;
	}
	
	/**
	 * Returns a representation of the Poly
	 * @return
	 */
	public SquareType[][] getPoly() {
		return poly;
	}
	
	public int getPolyLength() {
		return poly.length;
	}
	
	/**
	 * Returns the y axis limit of the Poly
	 * @return
	 */
	public int getPolyYlimit() {
		int yLimit = 0;
		for(int y = 0; y < this.getPolyLength(); y++) {
			for(int x = 0; x < this.getPolyLength(); x++) {
				if(this.getPoly()[x][y] != SquareType.EMPTY) {
					yLimit = y;
				}
			}
		}
		return yLimit;
	}
	
	/**
	 * Returns the right x axis limit of the Poly
	 * @return
	 */
	public int getPolyRightXlimit() {
		int xLimit = 0;
		for(int y = 0; y < this.getPolyLength(); y++) {
			for(int x = 0; x < this.getPolyLength(); x++) {
				if(this.getPoly()[x][y] != SquareType.EMPTY) {
					xLimit = x;
				}
			}
		}
		return xLimit;
	}
	
	/**
	 * Returns the left x axis limit of the Poly
	 * @return
	 */
	public int getPolyLefttXlimit() {
		int xLimit = 0;
		for(int y = 0; y < this.getPolyLength(); y++) {
			for(int x = 0; x < this.getPolyLength(); x++) {
				if(this.getPoly()[x][y] != SquareType.EMPTY) {
					xLimit = x;
					break; // leaves the inner loop if we found a Squaretype
				}
			}
		}
		return xLimit;
	}

}
