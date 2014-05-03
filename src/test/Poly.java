package test;

public class Poly {
	
	private SquareType[][] poly;
	
	/**
	 * 
	 * @param poly
	 */
	public Poly(SquareType[][] poly) {
		this.poly = poly;
	}
	
	/**
	 * 
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

}
